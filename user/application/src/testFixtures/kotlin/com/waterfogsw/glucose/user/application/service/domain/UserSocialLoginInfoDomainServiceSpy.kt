package com.waterfogsw.glucose.user.application.service.domain

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
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
