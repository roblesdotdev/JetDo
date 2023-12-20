package com.roblesdotdev.jetdo.login.domain.usecase

import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.LoginResponse

interface LoginUseCase {
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>
}
