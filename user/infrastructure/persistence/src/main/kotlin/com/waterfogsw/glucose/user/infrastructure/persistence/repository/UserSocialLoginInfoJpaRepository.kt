package com.waterfogsw.glucose.user.infrastructure.persistence.repository

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserSocialLoginInfoJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserSocialLoginInfoJpaRepository : JpaRepository<UserSocialLoginInfoJpaEntity, UUID> {

    @Query("SELECT u FROM UserSocialLoginInfoJpaEntity u WHERE u.sub = :sub AND u.oAuth2Provider = :provider")
    fun findBySubAndOAuth2Provider(
        @Param("sub") sub: String,
        @Param("provider") provider: OAuth2Provider
    ): UserSocialLoginInfoJpaEntity?

}
