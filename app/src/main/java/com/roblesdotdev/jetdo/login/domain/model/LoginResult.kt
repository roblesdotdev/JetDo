package com.roblesdotdev.jetdo.login.domain.model

sealed class LoginResult {
    data object Success : LoginResult()

    sealed class Failure : LoginResult() {
        data object InvalidCredentials : Failure()

        data object Unknown : Failure()

        data class EmptyCredentials(
            val emptyEmail: Boolean,
            val emptyPassword: Boolean
        ) : Failure()
    }
}
