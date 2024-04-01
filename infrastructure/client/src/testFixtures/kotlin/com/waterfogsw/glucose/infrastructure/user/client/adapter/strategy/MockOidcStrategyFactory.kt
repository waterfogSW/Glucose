package com.waterfogsw.glucose.infrastructure.user.client.adapter.strategy

import com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy.OidcStrategy
import com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy.OidcStrategyFactory
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.domain.user.enums.Provider

class MockOidcStrategyFactory : OidcStrategyFactory {

    class MockStrategy : OidcStrategy {
        override fun getToken(authorizationCode: String): String {
            return "token"
        }

        override fun getTokenInfo(idToken: String): OidcStrategy.IdTokenInfo {
            return OidcStrategy.IdTokenInfo(
                sub = "sub",
                name = "name",
                email = Email("test@test.com"),
                picture = URL("https://test.com/1.jpg")
            )
        }
    }

    override fun getStrategy(provider: Provider): OidcStrategy {
        return MockStrategy()
    }

}
