package com.waterfogsw.glucose.user.bootstrap

import com.waterfogsw.glucose.user.application.common.config.ApplicationConfig
import com.waterfogsw.glucose.user.infrastructure.client.common.config.ClientConfig
import com.waterfogsw.glucose.user.infrastructure.persistence.common.config.PersistenceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import java.util.*

@SpringBootApplication
@Import(
    value = [
        ApplicationConfig::class,
        ClientConfig::class,
        PersistenceConfig::class,
    ]
)
class UserHttpApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    runApplication<UserHttpApplication>(*args)
}
