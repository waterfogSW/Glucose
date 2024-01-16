package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.domain.enums.Provider
import java.util.*

object UserSocialLoginInfoTestFixture {

    fun create(
        id: UUID = UUID.randomUUID(),
        email: Email = Email("test@test.com"),
        userId: UUID = UUID.randomUUID(),
        provider: Provider = Provider.KAKAO
    ): UserSocialLoginInfo {
        return UserSocialLoginInfo(
            id = id,
            email = email,
            userId = userId,
            provider = provider
        )
    }
}
