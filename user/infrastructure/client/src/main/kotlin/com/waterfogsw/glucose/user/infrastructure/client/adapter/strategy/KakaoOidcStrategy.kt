package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL
import com.waterfogsw.glucose.user.infrastructure.client.api.KakaoOidcApi
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenInfoResponse
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenRequest
import com.waterfogsw.glucose.user.infrastructure.client.common.properties.OidcClientProperties
import org.springframework.stereotype.Component

@Component
class KakaoOidcStrategy (
    private val kakaoOidcApi: KakaoOidcApi,
    private val oidcClientProperties: OidcClientProperties,
): OidcStrategy {
    companion object {
        private const val PROVIDER_NAME = "kakao"
    }

    override fun getIdToken(authorizationCode: String): String {
        val getTokenRequest = GetTokenRequest(
            clientId = oidcClientProperties.clients[PROVIDER_NAME]!!.clientId,
            clientSecret = oidcClientProperties.clients[PROVIDER_NAME]!!.clientSecret,
            redirectUri = oidcClientProperties.clients[PROVIDER_NAME]!!.redirectUri,
            code = authorizationCode,
        )

        return kakaoOidcApi.getToken(getTokenRequest).idToken
    }

    override fun getUserInfoByIdToken(idToken: String): OidcStrategy.IdTokenInfo {
        val response: GetTokenInfoResponse = kakaoOidcApi.getTokenInfo(idToken)

        return OidcStrategy.IdTokenInfo(
            sub = response.sub,
            name = response.nickname,
            email = Email(response.email),
            picture = URL(response.picture),
        )
    }
}
