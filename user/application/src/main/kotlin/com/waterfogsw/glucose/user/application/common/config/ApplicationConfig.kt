package com.waterfogsw.glucose.user.application.common.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.waterfogsw.glucose.user.application"], lazyInit = true)
class ApplicationConfig
