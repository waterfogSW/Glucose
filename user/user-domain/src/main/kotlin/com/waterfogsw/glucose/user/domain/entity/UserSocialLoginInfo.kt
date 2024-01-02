package com.waterfogsw.glucose.user.domain.entity

import com.exapmle.ulid.UlidUtil
import java.time.LocalDateTime
import java.util.*

data class UserSocialLoginInfo(
    val id: UUID = UlidUtil.createUlid(),
    val sub: String,
    val userId: UUID,
    val provider: Provider,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun create(
            sub: String,
            userId: UUID,
            provider: Provider,
        ): UserSocialLoginInfo {
            return UserSocialLoginInfo(
                sub = sub,
                userId = userId,
                provider = provider,
            )
        }
    }
}


enum class Provider {
    KAKAO,
    GOOGLE,
}
