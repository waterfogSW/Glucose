package com.waterfogsw.glucose.application.user.port.outbound

import com.waterfogsw.glucose.domain.user.entity.User
import java.util.*

interface UserRepository {

    fun save(user: User)
    fun findById(id: UUID): User?

}
