package com.waterfogsw.glucose.user.application.common.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.waterfogsw.glucose.user.application"], lazyInit = true)
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = ["com.waterfogsw.glucose.user.application"])
class ApplicationConfig
