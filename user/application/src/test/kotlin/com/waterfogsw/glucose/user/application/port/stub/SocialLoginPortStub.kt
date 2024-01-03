package com.waterfogsw.glucose.user.application.port.stub

import com.waterfogsw.glucose.user.application.port.SocialLoginPort
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.domain.vo.Email

class SocialLoginPortStub: SocialLoginPort {
    override fun getUserInfo(authorizationCode: String, oAuth2Provider: OAuth2Provider): SocialLoginPort.UserInfo {
        return SocialLoginPort.UserInfo(
            sub = "test",
            name = "test",
            email = Email("test@test.com")
        )
    }

}
