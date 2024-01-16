package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL

interface OidcPort {

    fun getUserInfo(
        authorizationCode: String,
        provider: Provider,
    ): UserInfo


    data class UserInfo(
        val sub: String,
        val name: String,
        val email: Email,
        val picture: URL? = null,
    )
}
