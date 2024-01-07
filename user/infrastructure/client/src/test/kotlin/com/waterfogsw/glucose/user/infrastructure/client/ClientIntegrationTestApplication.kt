package com.waterfogsw.glucose.user.infrastructure.client

import com.waterfogsw.glucose.user.infrastructure.client.common.config.ClientConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    value = [
        ClientConfig::class
    ]
)
class ClientIntegrationTestApplication
