package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import java.util.*

object OAuth2UserInfoTestFixture {

    fun create(
        id: UUID = UUID.randomUUID(),
        sub: String = "test",
        userId: UUID = UUID.randomUUID(),
        oAuth2Provider: OAuth2Provider = OAuth2Provider.KAKAO
    ): UserSocialLoginInfo {
        return UserSocialLoginInfo(
            id = id,
            sub = sub,
            userId = userId,
            oAuth2Provider = oAuth2Provider
        )
    }
}
