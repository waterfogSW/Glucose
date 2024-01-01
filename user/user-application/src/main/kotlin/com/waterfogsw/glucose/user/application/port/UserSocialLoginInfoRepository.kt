package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.Provider
import com.waterfogsw.glucose.user.domain.entity.UserLoginInfo

interface UserSocialLoginInfoRepository {

    fun save(userLoginInfo: UserLoginInfo)
    fun findBySubAndProvider(
        sub: String,
        provider: Provider,
    ): UserLoginInfo?
}
