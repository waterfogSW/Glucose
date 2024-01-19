package com.waterfogsw.glucose.user.application.service.domain

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.user.domain.entity.User
import java.util.*


interface UserDomainService {

    fun create(
        name: String,
        email: Email,
        picture: URL?,
    ): User

    fun getById(id: UUID): User

}
