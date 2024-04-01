package com.waterfogsw.glucose.infrastructure.user.repository

import com.waterfogsw.glucose.infrastructure.user.entity.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserJpaRepository : JpaRepository<UserJpaEntity, UUID>
