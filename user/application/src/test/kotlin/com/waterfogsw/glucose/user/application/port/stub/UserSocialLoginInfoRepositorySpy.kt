package com.waterfogsw.glucose.user.application.port.stub

import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserSocialLoginInfoRepositorySpy : UserSocialLoginInfoRepository {

    private val userSocialLoginInfoMap: MutableMap<UUID, UserSocialLoginInfo> =
        ConcurrentHashMap<UUID, UserSocialLoginInfo>()

    override fun save(userSocialLoginInfo: UserSocialLoginInfo) {
        userSocialLoginInfoMap[userSocialLoginInfo.id] = userSocialLoginInfo
    }

    override fun findBySubAndProvider(
        sub: String,
        oAuth2Provider: OAuth2Provider
    ): UserSocialLoginInfo? {
        return userSocialLoginInfoMap.values.find { it.sub == sub && it.oAuth2Provider == oAuth2Provider }
    }

    fun existsByUserId(userId: UUID): Boolean {
        return userSocialLoginInfoMap.values.any { it.userId == userId }
    }
}
