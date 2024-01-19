package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.support.ulid.UlidUtil
import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID = UlidUtil.createUlid(),
    val name: String,
    val email: Email,
    val statusMessage: String? = null,
    val picture: URL? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {

    init {
        require(name.length in 2..20) { "이름은 2자 이상 10자 이하여야 합니다." }
        require((statusMessage?.length ?: 0) <= 100) { "상태 메시지는 100자를 넘을 수 없습니다." }
    }

    companion object {
        fun create(
            name: String,
            email: Email,
            picture: URL? = null,
        ): User {
            return User(
                name = name,
                email = email,
                picture = picture,
            )
        }
    }
}
