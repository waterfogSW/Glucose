package com.waterfogsw.glucose.bootstrap.user.controller

import com.waterfogsw.glucose.application.user.port.inbound.SocialLogin
import com.waterfogsw.glucose.bootstrap.user.api.AuthApi
import com.waterfogsw.glucose.bootstrap.user.dto.RefreshLoginTokenResponse
import com.waterfogsw.glucose.bootstrap.user.dto.SocialLoginResponse
import com.waterfogsw.glucose.domain.user.enums.Provider
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthApiController(
    private val userSocialLoginUseCase: SocialLogin,
) : AuthApi {

    override fun socialLogin(provider: Provider, code: String): SocialLoginResponse {
        val result = userSocialLoginUseCase.invoke(
            SocialLogin.Command(
                provider = provider,
                authorizationCode = code,
            )
        )

        return when (result) {
            is SocialLogin.Result.Success -> SocialLoginResponse.Success(
                accessToken = result.accessToken,
                refreshToken = result.refreshToken,
            )

            is SocialLogin.Result.UserNotRegistered -> SocialLoginResponse.UserNotRegistered(
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
