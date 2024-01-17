package com.waterfogsw.glucose.user.application.service.domain

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.common.support.vo.URL
import com.waterfogsw.glucose.user.domain.entity.User
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

    fun findById(id: UUID): User? {
        return userBucket[id]
    }

    fun existsByEmail(email: Email): Boolean {
        return userBucket.values.any { it.email == email }
    }
}
