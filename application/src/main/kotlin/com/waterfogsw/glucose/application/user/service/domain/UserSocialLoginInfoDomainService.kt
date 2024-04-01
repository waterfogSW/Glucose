package com.waterfogsw.glucose.application.user.service.domain

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.domain.user.enums.Provider
import java.util.*

interface UserSocialLoginInfoDomainService {

    fun create(
        userId: UUID,
        email: Email,
        provider: Provider,
    ): UserSocialLoginInfo

    fun findByEmail(email: Email): UserSocialLoginInfo?

}
