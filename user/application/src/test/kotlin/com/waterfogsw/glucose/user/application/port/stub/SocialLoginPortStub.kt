package com.waterfogsw.glucose.user.application.port.stub

import com.waterfogsw.glucose.user.application.port.OidcPort
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.domain.vo.Email

class SocialLoginPortStub : OidcPort {
    override fun getUserInfo(
        authorizationCode: String,
        oAuth2Provider: OAuth2Provider
    ): OidcPort.UserInfo {
        return OidcPort.UserInfo(
            sub = "test",
            name = "test",
            email = Email("test@test.com")
        )
    }

}
