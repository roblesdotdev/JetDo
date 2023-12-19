package com.roblesdotdev.jetdo.login.ui

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isSubmitting: Boolean = false
)
