package com.waterfogsw.glucose.infrastructure.application.service.domain

import com.waterfogsw.glucose.application.user.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.domain.user.enums.Provider
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserSocialLoginInfoDomainServiceSpy : UserSocialLoginInfoDomainService {

    private val userSocialLoginInfoBucket = ConcurrentHashMap<UUID, UserSocialLoginInfo>()

    override fun create(
        userId: UUID,
        email: Email,
        provider: Provider
    ): UserSocialLoginInfo {
        return UserSocialLoginInfo.create(
            userId = userId,
            email = email,
            provider = provider,
        ).also {
            userSocialLoginInfoBucket[it.id] = it
        }
    }

    override fun findByEmail(email: Email): UserSocialLoginInfo? {
        return userSocialLoginInfoBucket.values.firstOrNull { it.email == email }
    }

    fun existsByUserId(userId: UUID): Boolean {
        return userSocialLoginInfoBucket.values.any { it.userId == userId }
    }
}
