package com.waterfogsw.glucose.application.user.common.security.jwt

enum class UserJwtTokenType(
    val value: String,
) {
    Access("access"),
    Refresh("refresh")
}
