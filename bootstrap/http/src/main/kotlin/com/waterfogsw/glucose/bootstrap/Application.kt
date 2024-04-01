package com.waterfogsw.glucose.bootstrap

import com.waterfogsw.glucose.application.user.common.config.ApplicationConfig
import com.waterfogsw.glucose.infrastructure.client.common.config.ClientConfig
import com.waterfogsw.glucose.infrastructure.common.config.PersistenceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import java.util.*

@SpringBootApplication
@Import(
    ApplicationConfig::class,
    PersistenceConfig::class,
    ClientConfig::class,
)
class Application

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    runApplication<Application>(*args)
}
