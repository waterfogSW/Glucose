package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.common.ulid.UlidUtil
import com.waterfogsw.glucose.user.domain.enums.Provider
import java.time.LocalDateTime
import java.util.*

data class UserSocialLoginInfo(
    val id: UUID = UlidUtil.createUlid(),
    val userId: UUID,
    val email: Email,
    val provider: Provider,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun create(
            userId: UUID,
            email: Email,
            provider: Provider,
        ): UserSocialLoginInfo {
            return UserSocialLoginInfo(
                userId = userId,
                email = email,
                provider = provider,
            )
        }
    }
}
