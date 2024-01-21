package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.support.jwt.util.JwtTokenProvider
import com.waterfogsw.glucose.user.infrastructure.client.api.KakaoOidcApi
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.KakaoOidcApiDto
import com.waterfogsw.glucose.user.infrastructure.client.common.properties.OidcClientProperties
import org.springframework.stereotype.Component

@Component
class KakaoOidcStrategy(
    private val kakaoOidcApi: KakaoOidcApi,
    private val oidcClientProperties: OidcClientProperties,
) : OidcStrategy {
    companion object {
        private const val PROVIDER_NAME = "kakao"
    }

    private val client: OidcClientProperties.Client
        get() = oidcClientProperties.clients[PROVIDER_NAME]
            ?: throw IllegalStateException("Client properties for $PROVIDER_NAME not found.")

    private val provider: OidcClientProperties.Provider
        get() = oidcClientProperties.providers[PROVIDER_NAME]
            ?: throw IllegalStateException("Provider properties for $PROVIDER_NAME not found.")

    override fun getToken(authorizationCode: String): String {
        val getTokenRequest = KakaoOidcApiDto.GetTokenRequest(
            clientId = client.clientId,
            clientSecret = client.clientSecret,
            redirectUri = client.redirectUri,
            code = authorizationCode,
        )
        return kakaoOidcApi.getToken(getTokenRequest).idToken
    }

    override fun getTokenInfo(idToken: String): OidcStrategy.IdTokenInfo {
        val jwtClaims = JwtTokenProvider.verifyTokenWithJwks(idToken, provider.baseUrl).getOrThrow()
        return OidcStrategy.IdTokenInfo(
            sub = jwtClaims.sub.toString(),
            name = jwtClaims.customClaims["nickname"] as String,
            email = Email(jwtClaims.customClaims["email"] as String),
            picture = URL(jwtClaims.customClaims["picture"] as String),
        )
    }
}
