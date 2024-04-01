package com.waterfogsw.glucose.application.user.common.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.waterfogsw.glucose.application"], lazyInit = true)
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = ["com.waterfogsw.glucose.application"])
class ApplicationConfig
