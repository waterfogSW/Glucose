package com.waterfogsw.glucose.user.infrastructure.persistence.common.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [JpaConfig::class])
@ComponentScan(basePackages = ["com.waterfogsw.glucose.user.infrastructure.persistence"])
class PersistenceConfig
