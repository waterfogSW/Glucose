package com.waterfogsw.glucose.user.application.port.inbound

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.user.domain.enums.Provider


fun interface SocialLogin {

    fun invoke(command: Command): Result

    data class Command(
        val authorizationCode: String,
        val provider: Provider,
    )

    sealed class Result {
        data class Success(
            val accessToken: String,
            val refreshToken: String,
        ) : Result()

        data class UserNotRegistered(
            val name: String,
            val email: Email,
            val provider: Provider,
            val picture: URL?,
        ) : Result()
    }
}
