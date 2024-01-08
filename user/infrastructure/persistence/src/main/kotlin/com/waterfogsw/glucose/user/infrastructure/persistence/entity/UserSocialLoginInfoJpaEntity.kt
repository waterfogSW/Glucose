package com.waterfogsw.glucose.user.infrastructure.persistence.entity

import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "user_social_login_info")
class UserSocialLoginInfoJpaEntity(
    id: UUID,
    sub: String,
    userId: UUID,
    oAuth2Provider: OAuth2Provider,
    createdAt: LocalDateTime,
) {

    @Id
    @Column(name = "id", nullable = false)
    var id: UUID = id
        private set

    @Column(name = "sub", nullable = false)
    var sub: String = sub
        private set

    @Column(name = "user_id", nullable = false)
    var userId: UUID = userId
        private set

    @Column(name = "oauth2_provider", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var oAuth2Provider: OAuth2Provider = oAuth2Provider
        private set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = createdAt
        private set


    companion object {
        fun toEntity(userSocialLoginInfo: UserSocialLoginInfo): UserSocialLoginInfoJpaEntity {
            return UserSocialLoginInfoJpaEntity(
                id = userSocialLoginInfo.id,
                sub = userSocialLoginInfo.sub,
                userId = userSocialLoginInfo.userId,
                oAuth2Provider = userSocialLoginInfo.oAuth2Provider,
                createdAt = userSocialLoginInfo.createdAt,
            )
        }
    }

    fun toDomain(): UserSocialLoginInfo {
        return UserSocialLoginInfo(
            id = this.id,
            sub = this.sub,
            userId = this.userId,
            oAuth2Provider = this.oAuth2Provider,
            createdAt = this.createdAt,
        )
    }

}
