package com.waterfogsw.glucose.infrastructure.application.port

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.application.user.port.outbound.UserOidcPort
import com.waterfogsw.glucose.domain.user.enums.Provider

class SocialLoginPortStubUser : UserOidcPort {
    override fun getUserInfo(
        authorizationCode: String,
        provider: Provider
    ): UserOidcPort.UserInfo {
        return UserOidcPort.UserInfo(
            sub = "test",
            name = "test",
            email = Email("test@test.com")
        )
    }

}
