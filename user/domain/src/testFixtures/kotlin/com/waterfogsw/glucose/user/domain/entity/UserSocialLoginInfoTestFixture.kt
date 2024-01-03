package com.waterfogsw.glucose.user.domain.entity

import java.util.*

object UserSocialLoginInfoTestFixture {

    fun create(
        id: UUID = UUID.randomUUID(),
        sub: String = "test",
        userId: UUID = UUID.randomUUID(),
        provider: Provider = Provider.KAKAO
    ): UserSocialLoginInfo {
        return UserSocialLoginInfo(
            id = id,
            sub = sub,
            userId = userId,
            provider = provider
        )
    }
}
