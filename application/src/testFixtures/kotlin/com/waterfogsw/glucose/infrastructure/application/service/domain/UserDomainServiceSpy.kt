package com.waterfogsw.glucose.infrastructure.application.service.domain

import com.waterfogsw.glucose.application.user.service.domain.UserDomainService
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.domain.user.entity.User
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserDomainServiceSpy : UserDomainService {

    private val userBucket = ConcurrentHashMap<UUID, User>()
    override fun create(
        name: String,
        email: Email,
        picture: URL?
    ): User {
        return User.create(
            name = name,
            email = email,
            picture = picture,
        ).also { userBucket[it.id] = it }
    }

    override fun getById(id: UUID): User {
        return userBucket[id] ?: throw NoSuchElementException()
    }

    fun save(user: User) {
        userBucket[user.id] = user
    }

    fun existsByEmail(email: Email): Boolean {
        return userBucket.values.any { it.email == email }
    }
}
