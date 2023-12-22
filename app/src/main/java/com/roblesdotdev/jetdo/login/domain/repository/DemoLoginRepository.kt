package com.roblesdotdev.jetdo.login.domain.repository

import com.roblesdotdev.jetdo.login.domain.model.AuthToken
import com.roblesdotdev.jetdo.login.domain.model.Credentials
import com.roblesdotdev.jetdo.login.domain.model.LoginResponse
import com.roblesdotdev.jetdo.login.domain.model.RefreshToken
import com.roblesdotdev.jetdo.login.domain.model.Token
import javax.inject.Inject

/*
 * This is a sample implementation that does not interact with any real data.
 */
class DemoLoginRepository @Inject constructor() : LoginRepository {
    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        val defaultToken = Token(
            authToken = AuthToken(""),
            refreshToken = RefreshToken("")
        )

        val defaultResponse = LoginResponse(defaultToken)

        return Result.success(defaultResponse)
    }
}
