package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.application.port.outbound.OidcPort
import com.waterfogsw.glucose.user.domain.enums.Provider

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
