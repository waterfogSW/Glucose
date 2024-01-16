package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email

class SocialLoginPortStub : OidcPort {
    override fun getUserInfo(
        authorizationCode: String,
        provider: Provider
    ): OidcPort.UserInfo {
        return OidcPort.UserInfo(
            sub = "test",
            name = "test",
            email = Email("test@test.com")
        )
    }

}
