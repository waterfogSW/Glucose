package com.waterfogsw.glucose.user.infrastructure.persistence.adapter

import com.waterfogsw.glucose.user.application.port.UserRepository
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserJpaEntity
import com.waterfogsw.glucose.user.infrastructure.persistence.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {

    override fun save(user: User) {
        UserJpaEntity
            .from(user)
            .apply { userJpaRepository.save(this) }
    }

}
