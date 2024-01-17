package com.waterfogsw.glucose.user.application.service.domain.impl

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.application.port.outbound.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.application.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserSocialLoginInfoDomainServiceImpl(
    private val userSocialLoginInfoRepository: UserSocialLoginInfoRepository,
): UserSocialLoginInfoDomainService {
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
