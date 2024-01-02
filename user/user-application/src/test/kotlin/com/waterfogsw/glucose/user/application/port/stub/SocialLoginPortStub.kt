package com.waterfogsw.glucose.user.application.port.stub

import com.waterfogsw.glucose.user.application.port.SocialLoginPort
import com.waterfogsw.glucose.user.domain.entity.Provider
import com.waterfogsw.glucose.user.domain.vo.Email

class SocialLoginPortStub: SocialLoginPort {
    override fun getUserInfo(authorizationCode: String, provider: Provider): SocialLoginPort.UserInfo {
        return SocialLoginPort.UserInfo(
            sub = "test",
            name = "test",
            email = Email("test@test.com")
        )
    }

}
