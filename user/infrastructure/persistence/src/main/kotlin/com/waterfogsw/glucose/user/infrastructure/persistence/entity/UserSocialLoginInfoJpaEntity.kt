package com.waterfogsw.glucose.user.infrastructure.persistence.entity

import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "user_social_login_info")
class UserSocialLoginInfoJpaEntity(
    id: UUID,
    email: String,
    userId: UUID,
    provider: Provider,
    createdAt: LocalDateTime,
) {

    @Id
    @Column(name = "id", nullable = false)
    var id: UUID = id
        private set

    @Column(name = "email", nullable = false)
    var email = email
        private set

    @Column(name = "user_id", nullable = false)
    var userId: UUID = userId
        private set

    @Column(name = "oauth2_provider", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var provider: Provider = provider
        private set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = createdAt
        private set


    companion object {
        fun toEntity(userOAuthInfo: UserOAuthInfo): UserSocialLoginInfoJpaEntity {
            return UserSocialLoginInfoJpaEntity(
                id = userOAuthInfo.id,
                email = userOAuthInfo.email.value,
                userId = userOAuthInfo.userId,
                provider = userOAuthInfo.provider,
                createdAt = userOAuthInfo.createdAt,
            )
        }
    }

    fun toDomain(): UserOAuthInfo {
        return UserOAuthInfo(
            id = this.id,
            email = Email(this.email),
            userId = this.userId,
            provider = this.provider,
            createdAt = this.createdAt,
        )
    }

}
