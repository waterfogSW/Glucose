package com.waterfogsw.glucose.user.domain.entity

import com.exapmle.ulid.UlidUtil
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL
import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID = UlidUtil.createUlid(),
    val username: String,
    val email: Email,
    val statusMessage: String? = null,
    val profileImage: URL? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {

    init {
        require((statusMessage?.length ?: 0) <= 100) { "상태 메시지는 100자를 넘을 수 없습니다." }
    }

    companion object {
        fun create(
            username: String,
            email: Email,
            statusMessage: String? = null,
            profileImage: URL? = null,
        ): User {
            return User(
                username = username,
                email = email,
                statusMessage = statusMessage,
                profileImage = profileImage,
            )
        }
    }
}
