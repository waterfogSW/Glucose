package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.UserLoginInfo

interface UserSocialLoginInfoRepository {

    fun save(userLoginInfo: UserLoginInfo)
    fun findBySub(sub: String): UserLoginInfo?
}
