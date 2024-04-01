package com.waterfogsw.glucose.application.user.port.outbound

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo

interface UserSocialLoginInfoRepository {

    fun save(userSocialLoginInfo: UserSocialLoginInfo)
    fun findByEmail(email: Email): UserSocialLoginInfo?
}
