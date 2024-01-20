package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.support.common.util.UuidCreator
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.user.domain.enums.Provider
import java.time.LocalDateTime
import java.util.*

data class UserSocialLoginInfo(
    val id: UUID = UuidCreator.create(),
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
