package com.waterfogsw.glucose.infrastructure.user.adapter

import com.waterfogsw.glucose.application.user.port.outbound.UserRepository
import com.waterfogsw.glucose.domain.user.entity.User
import com.waterfogsw.glucose.infrastructure.user.entity.UserJpaEntity
import com.waterfogsw.glucose.infrastructure.user.repository.UserJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {

    override fun save(user: User) {
        UserJpaEntity
            .from(user)
            .apply { userJpaRepository.save(this) }
    }

    override fun findById(id: UUID): User? {
        return userJpaRepository.findById(id)
            .map { it.toDomain() }
            .orElse(null)
    }

}
