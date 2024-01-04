package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.common.ulid.UlidUtil
import java.time.LocalDateTime
import java.util.*

data class UserSocialLoginInfo(
    val id: UUID = UlidUtil.createUlid(),
    val sub: String,
    val userId: UUID,
    val oAuth2Provider: OAuth2Provider,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun create(
            sub: String,
            userId: UUID,
            oAuth2Provider: OAuth2Provider,
        ): UserSocialLoginInfo {
            return UserSocialLoginInfo(
                sub = sub,
                userId = userId,
                oAuth2Provider = oAuth2Provider,
            )
        }
    }
}
