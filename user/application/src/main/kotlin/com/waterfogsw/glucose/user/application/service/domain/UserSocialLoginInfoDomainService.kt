package com.waterfogsw.glucose.user.application.service.domain

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
import java.util.UUID

interface UserSocialLoginInfoDomainService {

    fun create(
        userId: UUID,
        email: Email,
        provider: Provider,
    ): UserSocialLoginInfo

    fun findByEmail(email: Email): UserSocialLoginInfo?

}
