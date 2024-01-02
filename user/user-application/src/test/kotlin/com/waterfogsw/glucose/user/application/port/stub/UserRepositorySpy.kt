package com.waterfogsw.glucose.user.application.port.stub

import com.waterfogsw.glucose.user.application.port.UserRepository
import com.waterfogsw.glucose.user.domain.entity.User
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserRepositorySpy : UserRepository {

    private val userMap: MutableMap<UUID, User> = ConcurrentHashMap<UUID, User>()

    override fun save(user: User) {
        userMap[user.id] = user
    }

    fun existsById(id: UUID): Boolean {
        return userMap.containsKey(id)
    }
}
