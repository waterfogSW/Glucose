package com.waterfogsw.glucose.user.infrastructure.client.common.properties


object OidcClientPropertiesFixture {

    fun createKakaoProperties(): OidcClientProperties {
        return OidcClientProperties(
            clients = mapOf(
                "kakao" to OidcClientProperties.Client(
                    clientId = "clientId",
                    clientSecret = "clientSecret",
                    redirectUri = "redirectUri",
                ),
            ),
            providers = mapOf(
                "kakao" to OidcClientProperties.Provider(
                    baseUrl = "baseUrl",
                    tokenEndpoint = "tokenEndpoint",
                    tokenInfoEndpoint = "tokenInfoEndpoint",
                ),
            ),
        )
    }

}
