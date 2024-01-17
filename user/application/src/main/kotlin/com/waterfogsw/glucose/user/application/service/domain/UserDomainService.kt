package com.waterfogsw.glucose.user.application.service.domain

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.common.support.vo.URL
import com.waterfogsw.glucose.user.domain.entity.User


interface UserDomainService {

    fun create(
        name: String,
        email: Email,
        picture: URL?,
    ): User

}
