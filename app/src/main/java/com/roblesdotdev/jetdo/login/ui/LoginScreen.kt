package com.roblesdotdev.jetdo.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.viewState.collectAsState()
    LoginContent(
        state = state.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClicked = {},
        onSignUpClicked = {}
    )
}
