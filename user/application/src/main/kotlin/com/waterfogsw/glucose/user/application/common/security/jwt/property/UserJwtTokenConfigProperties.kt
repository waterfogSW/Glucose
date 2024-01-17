package com.waterfogsw.glucose.user.application.common.security.jwt.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(value = "user.jwt")
data class UserJwtTokenConfigProperties(
    val access: TokenProperties,
    val refresh: TokenProperties,
) {
    data class TokenProperties(
        val expireSeconds: Long,
        val secret: String
    )
}
