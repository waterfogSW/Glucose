package com.waterfogsw.glucose.infrastructure.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = ["com.waterfogsw.glucose.infrastructure"])
@EnableJpaRepositories(basePackages = ["com.waterfogsw.glucose.infrastructure"])
class JpaConfig
