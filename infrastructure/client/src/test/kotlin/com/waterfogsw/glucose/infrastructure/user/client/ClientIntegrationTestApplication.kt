package com.waterfogsw.glucose.infrastructure.user.client

import com.waterfogsw.glucose.infrastructure.client.common.config.ClientConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    value = [
        ClientConfig::class
    ]
)
class ClientIntegrationTestApplication
