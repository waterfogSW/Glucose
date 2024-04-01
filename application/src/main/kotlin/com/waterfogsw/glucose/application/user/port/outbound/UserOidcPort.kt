package com.waterfogsw.glucose.application.user.port.outbound

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.domain.user.enums.Provider

fun interface UserOidcPort {

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
