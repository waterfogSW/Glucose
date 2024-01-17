package com.waterfogsw.glucose.user.application.common.security.jwt

enum class UserJwtTokenType(
    val value: String,
) {
    Access("access"),
    Refresh("refresh")
}
