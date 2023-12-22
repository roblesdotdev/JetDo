package com.roblesdotdev.jetdo.login.domain.usecase

import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.LoginResult
import kotlinx.coroutines.delay

class CredentialsLoginUseCase {

    suspend fun login(credentials: Credentials): LoginResult {
        @Suppress("MagicNumber")
        delay(2000)
        val validationResult = validateCredentials(credentials)

        if (validationResult != null) {
            return validationResult
        }

        return LoginResult.Failure.InvalidCredentials
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
