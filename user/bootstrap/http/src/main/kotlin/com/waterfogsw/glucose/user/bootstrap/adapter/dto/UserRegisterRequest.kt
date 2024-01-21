package com.waterfogsw.glucose.user.bootstrap.adapter.dto

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.user.domain.enums.Provider


data class UserRegisterRequest(
    val name: String,
    val email: Email,
    val picture: URL?,
    val provider: Provider,
)
