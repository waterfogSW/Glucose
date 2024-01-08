package com.waterfogsw.glucose.user.infrastructure.persistence.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = ["com.waterfogsw.glucose.user.infrastructure.persistence.adapter.entity"])
@EnableJpaRepositories(basePackages = ["com.waterfogsw.glucose.user.infrastructure.persistence.repository"])
class JpaConfig
