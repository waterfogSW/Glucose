package com.waterfogsw.glucose.user.application.port

import com.waterfogsw.glucose.user.domain.entity.User

interface UserRepository {

    fun save(user: User)

}
