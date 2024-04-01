package com.waterfogsw.glucose.infrastructure.common.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [JpaConfig::class])
@ComponentScan(basePackages = ["com.waterfogsw.glucose.infrastructure"])
class PersistenceConfig
