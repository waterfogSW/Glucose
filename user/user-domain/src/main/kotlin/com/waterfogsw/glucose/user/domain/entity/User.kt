package com.waterfogsw.glucose.user.domain.entity

import com.exapmle.ulid.UlidUtil
import com.waterfogsw.glucose.user.domain.vo.URL
import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID = UlidUtil.createUlid(),
    val username: String,
    val statusMessage: String? = null,
    val profileImage: URL? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
