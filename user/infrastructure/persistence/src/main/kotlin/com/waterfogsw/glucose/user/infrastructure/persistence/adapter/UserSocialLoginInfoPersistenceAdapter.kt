package com.waterfogsw.glucose.user.infrastructure.persistence.adapter

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.application.port.outbound.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserSocialLoginInfoJpaEntity
import com.waterfogsw.glucose.user.infrastructure.persistence.repository.UserSocialLoginInfoJpaRepository
import org.springframework.stereotype.Component

@Component
class UserSocialLoginInfoPersistenceAdapter(
    private val userSocialLoginInfoJpaRepository: UserSocialLoginInfoJpaRepository
) : UserSocialLoginInfoRepository {

    override fun save(userSocialLoginInfo: UserSocialLoginInfo) {
        UserSocialLoginInfoJpaEntity.toEntity(userSocialLoginInfo)
            .apply { userSocialLoginInfoJpaRepository.save(this) }
    }

    override fun findByEmail(email: Email): UserSocialLoginInfo? {
        return userSocialLoginInfoJpaRepository.findByEmail(email = email.value)?.toDomain()
    }


}
