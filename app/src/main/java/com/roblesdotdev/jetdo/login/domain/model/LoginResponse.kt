package com.roblesdotdev.jetdo.login.domain.model

/**
 * A response for any request to login to an external service.
 */
data class LoginResponse(
    val token: Token
)
