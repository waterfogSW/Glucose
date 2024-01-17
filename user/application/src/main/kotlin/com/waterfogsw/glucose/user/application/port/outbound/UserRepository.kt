package com.waterfogsw.glucose.user.application.port.outbound

import com.waterfogsw.glucose.user.domain.entity.User
import java.util.*

interface UserRepository {

    fun save(user: User)
    fun findById(id: UUID): User?

}
