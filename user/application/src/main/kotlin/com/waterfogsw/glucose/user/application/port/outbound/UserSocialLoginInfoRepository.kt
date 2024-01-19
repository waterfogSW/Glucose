package com.waterfogsw.glucose.user.application.port.outbound

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo

interface UserSocialLoginInfoRepository {

    fun save(userSocialLoginInfo: UserSocialLoginInfo)
    fun findByEmail(email: Email): UserSocialLoginInfo?
}
