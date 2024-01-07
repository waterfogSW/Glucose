package com.waterfogsw.glucose.user.infrastructure.client.common.config

import feign.Logger
import feign.Retryer
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.waterfogsw.glucose.user.infrastructure.client"])
@ConfigurationPropertiesScan(basePackages = ["com.waterfogsw.glucose.user.infrastructure.client.common.properties"])
@EnableFeignClients(basePackages = ["com.waterfogsw.glucose.user.infrastructure.client"])
class ClientConfig {

    @Bean
    fun retryer(): Retryer.Default {
        return Retryer.Default(100, 1000, 5)
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

}
