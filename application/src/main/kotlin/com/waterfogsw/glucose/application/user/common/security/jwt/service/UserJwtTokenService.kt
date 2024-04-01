package com.waterfogsw.glucose.application.user.common.security.jwt.service

import com.waterfogsw.glucose.domain.user.entity.User

interface UserJwtTokenService {

    fun createAccessToken(user: User): String

    fun createRefreshToken(user: User): String
}
