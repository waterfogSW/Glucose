package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.enums.Provider
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
