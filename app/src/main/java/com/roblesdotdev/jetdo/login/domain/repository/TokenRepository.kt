package com.roblesdotdev.jetdo.login.domain.repository

import com.roblesdotdev.jetdo.login.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun storeToken(
        token: Token
    )

    suspend fun clearToken()

    fun observeToken(): Flow<Token?>
}
