package com.waterfogsw.glucose.user.application.port.outbound

import com.waterfogsw.glucose.user.domain.entity.User

interface UserRepository {

    fun save(user: User)

}
