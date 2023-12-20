package com.roblesdotdev.jetdo.login.domain.repository

import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.LoginResponse

interface LoginRepository {
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>
}
