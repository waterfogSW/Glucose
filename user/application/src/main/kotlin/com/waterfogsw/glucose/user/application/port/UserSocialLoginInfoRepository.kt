package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider

interface UserSocialLoginInfoRepository {

    fun save(userSocialLoginInfo: UserSocialLoginInfo)
    fun findBySubAndProvider(
        sub: String,
        oAuth2Provider: OAuth2Provider,
    ): UserSocialLoginInfo?
}
