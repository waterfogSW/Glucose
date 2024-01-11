package com.waterfogsw.glucose.user.bootstrap.adapter.controller

import com.waterfogsw.glucose.user.bootstrap.adapter.api.AuthApi
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.RefreshLoginTokenResponse
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.SocialLoginResponse
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthApiController : AuthApi {
    override fun socialLogin(provider: String, code: String): SocialLoginResponse {
        TODO("Not yet implemented")
    }

    override fun refreshLoginToken(refreshToken: String): RefreshLoginTokenResponse {
        TODO("Not yet implemented")
    }
}
