package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import com.waterfogsw.glucose.user.domain.vo.Email

interface UserSocialLoginInfoRepository {

    fun save(userOAuthInfo: UserOAuthInfo)
    fun findByEmail(email: Email): UserOAuthInfo?
}
