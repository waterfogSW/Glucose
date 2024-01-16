package com.waterfogsw.glucose.user.infrastructure.client.adapter

import com.waterfogsw.glucose.user.application.port.OidcPort
import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy.OidcStrategy
import com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy.OidcStrategyFactory
import org.springframework.stereotype.Component


@Component
class OidcAdapter(
    private val oidcStrategyFactory: OidcStrategyFactory
) : OidcPort {

    override fun getUserInfo(
        authorizationCode: String,
        provider: Provider
    ): OidcPort.UserInfo {
        val loginStrategy: OidcStrategy = oidcStrategyFactory.getStrategy(provider)

        val idToken: String = loginStrategy.getToken(authorizationCode)
        val idTokenInfo: OidcStrategy.IdTokenInfo = loginStrategy.getTokenInfo(idToken)

        return OidcPort.UserInfo(
            sub = idTokenInfo.sub,
            name = idTokenInfo.name,
            email = idTokenInfo.email,
            picture = idTokenInfo.picture,
        )
    }
}
