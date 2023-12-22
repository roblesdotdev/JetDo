package com.roblesdotdev.jetdo.login.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginComplete: () -> Unit
) {
    val state = viewModel.viewState.collectAsState()

    DisposableEffect(state.value) {
        if (state.value is LoginViewState.Completed) {
            onLoginComplete.invoke()
        }

        onDispose { }
    }

    val context = LocalContext.current.applicationContext

    LoginContent(
        state = state.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClicked = viewModel::loginButtonClicked,
        onSignUpClicked = {
            Toast.makeText(context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
        }
    )
}
