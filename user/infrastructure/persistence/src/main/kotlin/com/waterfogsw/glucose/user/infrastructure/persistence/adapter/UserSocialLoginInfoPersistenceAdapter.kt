package com.waterfogsw.glucose.user.infrastructure.persistence.adapter

import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserSocialLoginInfoJpaEntity
import com.waterfogsw.glucose.user.infrastructure.persistence.repository.UserSocialLoginInfoJpaRepository
import org.springframework.stereotype.Component

@Component
class UserSocialLoginInfoPersistenceAdapter (
    private val userSocialLoginInfoJpaRepository: UserSocialLoginInfoJpaRepository
): UserSocialLoginInfoRepository {

    override fun save(userSocialLoginInfo: UserSocialLoginInfo) {
        UserSocialLoginInfoJpaEntity.toEntity(userSocialLoginInfo)
            .apply { userSocialLoginInfoJpaRepository.save(this) }
    }

    override fun findBySubAndProvider(
        sub: String,
        oAuth2Provider: OAuth2Provider
    ): UserSocialLoginInfo? {
        return userSocialLoginInfoJpaRepository.findBySubAndOAuth2Provider(sub, oAuth2Provider)
            ?.toDomain()
    }


}
