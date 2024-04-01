package com.waterfogsw.glucose.application.user.service.domain.impl

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.application.user.port.outbound.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.application.user.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.domain.user.enums.Provider
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserSocialLoginInfoDomainServiceImpl(
    private val userSocialLoginInfoRepository: UserSocialLoginInfoRepository,
) : UserSocialLoginInfoDomainService {
    override fun create(
        userId: UUID,
        email: Email,
        provider: Provider
    ): UserSocialLoginInfo {
        return UserSocialLoginInfo.create(
            userId = userId,
            email = email,
            provider = provider,
        ).also {
            userSocialLoginInfoRepository.save(it)
        }
    }

    override fun findByEmail(email: Email): UserSocialLoginInfo? {
        return userSocialLoginInfoRepository.findByEmail(email)
    }
}
