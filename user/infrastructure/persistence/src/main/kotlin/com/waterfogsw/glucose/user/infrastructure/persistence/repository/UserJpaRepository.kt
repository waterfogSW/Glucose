package com.waterfogsw.glucose.user.infrastructure.persistence.repository

import com.waterfogsw.glucose.user.infrastructure.persistence.entity.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserJpaRepository : JpaRepository<UserJpaEntity, UUID>
