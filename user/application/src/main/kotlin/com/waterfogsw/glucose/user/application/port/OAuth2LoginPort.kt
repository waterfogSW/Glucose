package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL

interface OAuth2LoginPort {

    fun getUserInfo(
        authorizationCode: String,
        oAuth2Provider: OAuth2Provider,
    ): UserInfo


    data class UserInfo(
        val sub: String,
        val name: String,
        val email: Email,
        val profileImage: URL? = null,
    )
}
