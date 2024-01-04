package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL

interface OidcStrategy {

    fun getIdToken(authorizationCode: String): String

    fun getUserInfoByIdToken(idToken: String): IdTokenInfo

    data class IdTokenInfo(
        val sub: String,
        val name: String,
        val email: Email,
        val picture: URL,
    )
}
