package com.waterfogsw.glucose.user.infrastructure.persistence.adapter

import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserSocialLoginInfoJpaEntity
import com.waterfogsw.glucose.user.infrastructure.persistence.repository.UserSocialLoginInfoJpaRepository
import org.springframework.stereotype.Component

@Component
class UserSocialLoginInfoPersistenceAdapter(
    private val userSocialLoginInfoJpaRepository: UserSocialLoginInfoJpaRepository
) : UserSocialLoginInfoRepository {

    override fun save(userOAuthInfo: UserOAuthInfo) {
        UserSocialLoginInfoJpaEntity.toEntity(userOAuthInfo)
            .apply { userSocialLoginInfoJpaRepository.save(this) }
    }

    override fun findByEmail(email: Email): UserOAuthInfo? {
        return userSocialLoginInfoJpaRepository.findByEmail(email = email.value)?.toDomain()
    }


}
