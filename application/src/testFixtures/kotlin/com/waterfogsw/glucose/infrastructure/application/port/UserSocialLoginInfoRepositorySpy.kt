package com.waterfogsw.glucose.infrastructure.application.port

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.application.user.port.outbound.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserSocialLoginInfoRepositorySpy : UserSocialLoginInfoRepository {

    private val userSocialLoginInfoMap: MutableMap<UUID, UserSocialLoginInfo> =
        ConcurrentHashMap<UUID, UserSocialLoginInfo>()

    override fun save(userSocialLoginInfo: UserSocialLoginInfo) {
        userSocialLoginInfoMap[userSocialLoginInfo.id] = userSocialLoginInfo
    }

    override fun findByEmail(email: Email): UserSocialLoginInfo? {
        return userSocialLoginInfoMap.values.find { it.email == email }
    }

    fun existsByUserId(userId: UUID): Boolean {
        return userSocialLoginInfoMap.values.any { it.userId == userId }
    }
}
