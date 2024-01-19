package com.waterfogsw.glucose.user.infrastructure.persistence.entity

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.user.domain.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "`user`")
class UserJpaEntity(
    id: UUID,
    username: String,
    email: String,
    statusMessage: String?,
    profileImage: String?,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime,
) {

    @Id
    @Column(name = "id", nullable = false)
    var id: UUID = id
        private set

    @Column(name = "username", nullable = false)
    var username: String = username
        private set

    @Column(name = "email", nullable = false)
    var email: String = email
        private set

    @Column(name = "status_message", nullable = true)
    var statusMessage: String? = statusMessage
        private set

    @Column(name = "profile_image", nullable = true)
    var profileImage: String? = profileImage
        private set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = createdAt
        private set

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = updatedAt
        private set

    companion object {
        fun from(user: User): UserJpaEntity {
            return UserJpaEntity(
                id = user.id,
                username = user.name,
                email = user.email.value,
                statusMessage = user.statusMessage,
                profileImage = user.picture?.value,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            name = username,
            email = Email(email),
            statusMessage = statusMessage,
            picture = profileImage?.let { URL(it) },
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

}
