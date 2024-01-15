package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.common.ulid.UlidUtil
import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import java.time.LocalDateTime
import java.util.*

data class UserOAuthInfo(
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
        ): UserOAuthInfo {
            return UserOAuthInfo(
                userId = userId,
                email = email,
                provider = provider,
            )
        }
    }
}
