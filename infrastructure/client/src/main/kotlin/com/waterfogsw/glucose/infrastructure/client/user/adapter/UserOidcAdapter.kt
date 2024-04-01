package com.waterfogsw.glucose.infrastructure.client.user.adapter

import com.waterfogsw.glucose.application.user.port.outbound.UserOidcPort
import com.waterfogsw.glucose.domain.user.enums.Provider
import com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy.OidcStrategy
import com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy.OidcStrategyFactory
import org.springframework.stereotype.Component


@Component
class UserOidcAdapter(
    private val oidcStrategyFactory: OidcStrategyFactory
) : UserOidcPort {

    override fun getUserInfo(
        authorizationCode: String,
        provider: Provider
    ): UserOidcPort.UserInfo {
        val loginStrategy: OidcStrategy = oidcStrategyFactory.getStrategy(provider)

        val idToken: String = loginStrategy.getToken(authorizationCode)
        val idTokenInfo: OidcStrategy.IdTokenInfo = loginStrategy.getTokenInfo(idToken)

        return UserOidcPort.UserInfo(
            sub = idTokenInfo.sub,
            name = idTokenInfo.name,
            email = idTokenInfo.email,
            picture = idTokenInfo.picture,
        )
    }
}
