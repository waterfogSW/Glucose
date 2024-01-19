package com.waterfogsw.glucose.user.bootstrap.adapter.controller

import com.waterfogsw.glucose.user.application.port.inbound.UserSocialLoginUseCase
import com.waterfogsw.glucose.user.bootstrap.adapter.api.AuthApi
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.RefreshLoginTokenResponse
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.SocialLoginResponse
import com.waterfogsw.glucose.user.domain.enums.Provider
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthApiController(
    private val userSocialLoginUseCase: UserSocialLoginUseCase,
) : AuthApi {

    override fun socialLogin(provider: Provider, code: String): SocialLoginResponse {
        val result = userSocialLoginUseCase.invoke(
            UserSocialLoginUseCase.Command(
                provider = provider,
                authorizationCode = code,
            )
        )

        return when (result) {
            is UserSocialLoginUseCase.Result.Success -> SocialLoginResponse.Success(
                accessToken = result.accessToken,
                refreshToken = result.refreshToken,
            )

            is UserSocialLoginUseCase.Result.UserNotRegistered -> SocialLoginResponse.UserNotRegistered(
                provider = result.provider,
                email = result.email,
                name = result.name,
                picture = result.picture,
            )
        }
    }

    override fun refreshLoginToken(refreshToken: String): RefreshLoginTokenResponse {
        TODO("Not yet implemented")
    }
}
