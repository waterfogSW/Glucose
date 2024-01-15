package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL

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
