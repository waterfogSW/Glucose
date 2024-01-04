package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL
import com.waterfogsw.glucose.user.infrastructure.client.api.KakaoOidcApi
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenInfoResponse
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenRequest
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

    override fun getToken(authorizationCode: String): String {
        val getTokenRequest = GetTokenRequest(
            clientId = client.clientId,
            clientSecret = client.clientSecret,
            redirectUri = client.redirectUri,
            code = authorizationCode,
        )
        return kakaoOidcApi.getToken(getTokenRequest).idToken
    }

    override fun getTokenInfo(idToken: String): OidcStrategy.IdTokenInfo {
        val response: GetTokenInfoResponse = kakaoOidcApi.getTokenInfo(idToken)

        return OidcStrategy.IdTokenInfo(
            sub = response.sub,
            name = response.nickname,
            email = Email(response.email),
            picture = URL(response.picture),
        )
    }
}
