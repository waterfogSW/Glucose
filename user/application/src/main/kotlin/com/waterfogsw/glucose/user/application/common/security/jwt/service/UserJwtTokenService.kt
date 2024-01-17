package com.waterfogsw.glucose.user.application.common.security.jwt.service

import com.waterfogsw.glucose.user.domain.entity.User

interface UserJwtTokenService {

    fun createAccessToken(user: User): String

    fun createRefreshToken(user: User): String
}
