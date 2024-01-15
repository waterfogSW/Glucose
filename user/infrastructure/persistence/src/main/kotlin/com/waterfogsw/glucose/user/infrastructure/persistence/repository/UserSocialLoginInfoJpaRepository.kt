package com.waterfogsw.glucose.user.infrastructure.persistence.repository

import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserSocialLoginInfoJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserSocialLoginInfoJpaRepository : JpaRepository<UserSocialLoginInfoJpaEntity, UUID> {

    fun findByEmail(email: String): UserSocialLoginInfoJpaEntity?

}
