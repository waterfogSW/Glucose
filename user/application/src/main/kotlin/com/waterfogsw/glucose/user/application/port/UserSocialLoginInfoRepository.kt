package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo

interface UserSocialLoginInfoRepository {

    fun save(userSocialLoginInfo: UserSocialLoginInfo)
    fun findByEmail(email: Email): UserSocialLoginInfo?
}
