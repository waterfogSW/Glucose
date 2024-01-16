package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import com.waterfogsw.glucose.user.domain.vo.Email
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserOAuthInfoRepositorySpy : UserOAuthInfoRepository {

    private val userOAuthInfoMap: MutableMap<UUID, UserOAuthInfo> =
        ConcurrentHashMap<UUID, UserOAuthInfo>()

    override fun save(userOAuthInfo: UserOAuthInfo) {
        userOAuthInfoMap[userOAuthInfo.id] = userOAuthInfo
    }

    override fun findByEmail(email: Email): UserOAuthInfo? {
        return userOAuthInfoMap.values.find { it.email == email }
    }

    fun existsByUserId(userId: UUID): Boolean {
        return userOAuthInfoMap.values.any { it.userId == userId }
    }
}
