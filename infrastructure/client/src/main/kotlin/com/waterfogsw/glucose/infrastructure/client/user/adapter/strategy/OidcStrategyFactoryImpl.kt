package com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy

import com.waterfogsw.glucose.domain.user.enums.Provider
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class OidcStrategyFactoryImpl(
    private val applicationContext: ApplicationContext
) : OidcStrategyFactory {
    override fun getStrategy(provider: Provider): OidcStrategy {
        return when (provider) {
            Provider.KAKAO -> {
                applicationContext.getBean(KakaoOidcStrategy::class.java)
            }

            Provider.GOOGLE -> TODO()
        }
    }

}
