package com.waterfogsw.glucose.user.infrastructure.client.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "oidc")
data class OidcClientProperties(
    val clients: Map<String, Client>,
    val providers: Map<String, Provider>,
) {

    data class Client(
        val clientId: String,
        val clientSecret: String,
        val redirectUri: String,
    )

    data class Provider(
        val baseUrl: String,
        val tokenEndpoint: String,
        val tokenInfoEndpoint: String,
    )
}
