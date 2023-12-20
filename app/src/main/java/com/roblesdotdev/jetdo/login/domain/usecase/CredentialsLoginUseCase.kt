package com.roblesdotdev.jetdo.login.domain.usecase

import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.InvalidCredentialsException
import com.roblesdotdev.jetdo.login.domain.model.LoginResult
import com.roblesdotdev.jetdo.login.domain.repository.LoginRepository
import com.roblesdotdev.jetdo.login.domain.repository.TokenRepository

class CredentialsLoginUseCase(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
) {

    suspend fun login(credentials: Credentials): LoginResult {
        val validationResult = validateCredentials(credentials)

        if (validationResult != null) {
            return validationResult
        }

        val repoResult = loginRepository.login(credentials)

        return repoResult.fold(
            onSuccess = { loginResponse ->
                tokenRepository.storeToken(loginResponse.token)
                LoginResult.Success
            },
            onFailure = { error ->
                loginResultForError(error)
            }
        )
    }

    private fun loginResultForError(error: Throwable): LoginResult.Failure {
        return when (error) {
            is InvalidCredentialsException -> {
                LoginResult.Failure.InvalidCredentials
            }

            else -> {
                LoginResult.Failure.Unknown
            }
        }
    }

    private fun validateCredentials(credentials: Credentials): LoginResult.Failure.EmptyCredentials? {
        val emptyEmail = credentials.email.value.isEmpty()
        val emptyPassword = credentials.password.value.isEmpty()

        return if (emptyEmail || emptyPassword) {
            LoginResult.Failure.EmptyCredentials(
                emptyEmail = emptyEmail,
                emptyPassword = emptyPassword
            )
        } else {
            null
        }
    }
}
