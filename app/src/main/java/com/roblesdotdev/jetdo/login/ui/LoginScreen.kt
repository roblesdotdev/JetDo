package com.roblesdotdev.jetdo.login.ui

import androidx.compose.runtime.Composable

@Composable
fun LoginScreen() {
    val state = LoginViewState()
    LoginContent(
        state = state,
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClicked = {},
        onSignUpClicked = {}
    )
}
