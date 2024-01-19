package com.waterfogsw.glucose.user.infrastructure.persistence.entity

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
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

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, columnDefinition = "varchar(255)")
    var provider: Provider = provider
        private set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = createdAt
        private set


    companion object {
        fun toEntity(userSocialLoginInfo: UserSocialLoginInfo): UserSocialLoginInfoJpaEntity {
            return UserSocialLoginInfoJpaEntity(
                id = userSocialLoginInfo.id,
                email = userSocialLoginInfo.email.value,
                userId = userSocialLoginInfo.userId,
                provider = userSocialLoginInfo.provider,
                createdAt = userSocialLoginInfo.createdAt,
            )
        }
    }

    fun toDomain(): UserSocialLoginInfo {
        return UserSocialLoginInfo(
            id = this.id,
            email = Email(this.email),
            userId = this.userId,
            provider = this.provider,
            createdAt = this.createdAt,
        )
    }

}
