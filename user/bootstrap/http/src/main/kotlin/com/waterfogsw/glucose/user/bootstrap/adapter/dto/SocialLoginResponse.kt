package com.waterfogsw.glucose.user.bootstrap.adapter.dto

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.user.domain.enums.Provider
import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    oneOf = [
        SocialLoginResponse.Success::class,
        SocialLoginResponse.UserNotRegistered::class,
    ],
)
sealed class SocialLoginResponse {

    data class Success(
        val accessToken: String,
        val refreshToken: String,
    ): SocialLoginResponse()

    data class UserNotRegistered(
        val name: String,
        val email: Email,
        val provider: Provider,
        val picture: URL?,
    ): SocialLoginResponse()
}
