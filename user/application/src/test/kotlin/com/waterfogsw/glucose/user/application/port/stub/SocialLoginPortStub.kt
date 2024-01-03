package com.waterfogsw.glucose.user.application.port.stub

import com.waterfogsw.glucose.user.application.port.OAuth2LoginPort
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.domain.vo.Email

class SocialLoginPortStub: OAuth2LoginPort {
    override fun getUserInfo(authorizationCode: String, oAuth2Provider: OAuth2Provider): OAuth2LoginPort.UserInfo {
        return OAuth2LoginPort.UserInfo(
            sub = "test",
            name = "test",
            email = Email("test@test.com")
        )
    }

}
