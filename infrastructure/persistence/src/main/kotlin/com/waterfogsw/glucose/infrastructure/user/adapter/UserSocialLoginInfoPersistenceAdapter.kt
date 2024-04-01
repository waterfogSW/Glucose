package com.waterfogsw.glucose.infrastructure.user.adapter

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.application.user.port.outbound.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.infrastructure.user.entity.UserSocialLoginInfoJpaEntity
import com.waterfogsw.glucose.infrastructure.user.repository.UserSocialLoginInfoJpaRepository
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
