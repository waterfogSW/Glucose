package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import java.util.*

object OAuth2UserInfoTestFixture {

    fun create(
        id: UUID = UUID.randomUUID(),
        email: Email = Email("test@test.com"),
        userId: UUID = UUID.randomUUID(),
        provider: Provider = Provider.KAKAO
    ): UserOAuthInfo {
        return UserOAuthInfo(
            id = id,
            email = email,
            userId = userId,
            provider = provider
        )
    }
}
