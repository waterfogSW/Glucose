package com.waterfogsw.glucose.user.infrastructure.persistence.repository

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserSocialLoginInfoJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserSocialLoginInfoJpaRepository : JpaRepository<UserSocialLoginInfoJpaEntity, UUID> {

    fun findBySubAndOAuth2Provider(
        sub: String,
        oAuth2Provider: OAuth2Provider
    ): UserSocialLoginInfoJpaEntity?

}
