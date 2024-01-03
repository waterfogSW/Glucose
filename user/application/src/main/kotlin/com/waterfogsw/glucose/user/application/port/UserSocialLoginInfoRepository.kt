package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.Provider
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo

interface UserSocialLoginInfoRepository {

    fun save(userSocialLoginInfo: UserSocialLoginInfo)
    fun findBySubAndProvider(
        sub: String,
        provider: Provider,
    ): UserSocialLoginInfo?
}
