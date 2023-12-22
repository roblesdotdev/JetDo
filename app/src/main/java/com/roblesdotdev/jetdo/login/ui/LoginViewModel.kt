package com.roblesdotdev.jetdo.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roblesdotdev.jetdo.R
import com.roblesdotdev.jetdo.core.utils.UIText
import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.Email
import com.roblesdotdev.jetdo.login.domain.model.LoginResult
import com.roblesdotdev.jetdo.login.domain.model.Password
import com.roblesdotdev.jetdo.login.domain.usecase.CredentialsLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val credentialsLoginUseCase: CredentialsLoginUseCase = CredentialsLoginUseCase()
) : ViewModel() {
    private val _viewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState.Initial)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun emailChanged(email: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedEmail(email)
        )
    }

    fun passwordChanged(password: String) {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedPassword(password)
        )
    }

    fun loginButtonClicked() {
        val currentCredentials = _viewState.value.credentials

        _viewState.value = LoginViewState.Submitting(credentials = currentCredentials)

        viewModelScope.launch {
            val loginResult = credentialsLoginUseCase.login(currentCredentials)

            handleLoginResult(loginResult, currentCredentials)
        }
    }

    private fun handleLoginResult(
        loginResult: LoginResult,
        currentCredentials: Credentials
    ) {
        _viewState.value = when (loginResult) {
            is LoginResult.Failure.EmptyCredentials -> {
                loginResult.toLoginViewState(currentCredentials)
            }

            LoginResult.Success -> {
                LoginViewState.Completed
            }

            else -> _viewState.value
        }
    }
}

private fun Credentials.withUpdatedEmail(email: String): Credentials {
    return this.copy(email = Email(email))
}

private fun Credentials.withUpdatedPassword(password: String): Credentials {
    return this.copy(password = Password(password))
}

private fun LoginResult.Failure.EmptyCredentials.toLoginViewState(credentials: Credentials): LoginViewState {
    return LoginViewState.Active(
        credentials = credentials,
        emailInputErrorMessage = UIText.ResourceText(R.string.error_empty_email).takeIf {
            this.emptyEmail
        },
        passwordInputErrorMessage = UIText.ResourceText(R.string.error_empty_password).takeIf {
            this.emptyPassword
        }
    )
}
