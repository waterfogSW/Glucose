package com.waterfogsw.glucose.infrastructure.user.repository

import com.waterfogsw.glucose.infrastructure.user.entity.UserSocialLoginInfoJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserSocialLoginInfoJpaRepository : JpaRepository<UserSocialLoginInfoJpaEntity, UUID> {

    fun findByEmail(email: String): UserSocialLoginInfoJpaEntity?

}
