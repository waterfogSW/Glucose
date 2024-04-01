package com.waterfogsw.glucose.infrastructure.domain.entity

import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.domain.user.enums.Provider
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
