package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL

interface SocialLoginPort {

    fun getUserInfo(
        authorizationCode: String,
        provider: Provider,
    ): UserInfo


    data class UserInfo(
        val sub: String,
        val name: String,
        val email: Email,
        val profileImage: URL? = null,
    )
}
