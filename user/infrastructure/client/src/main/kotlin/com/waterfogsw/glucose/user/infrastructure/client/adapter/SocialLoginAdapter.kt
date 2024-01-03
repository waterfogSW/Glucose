package com.waterfogsw.glucose.user.infrastructure.client.adapter

import com.waterfogsw.glucose.user.application.port.OAuth2LoginPort
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import org.springframework.stereotype.Component


@Component
class SocialLoginAdapter : OAuth2LoginPort {

    override fun getUserInfo(
        authorizationCode: String,
        oAuth2Provider: OAuth2Provider
    ): OAuth2LoginPort.UserInfo {
        TODO()
    }

}
