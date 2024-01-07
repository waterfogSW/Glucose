package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class OidcStrategyFactoryImpl(
    private val applicationContext: ApplicationContext
): OidcStrategyFactory {
    override fun getStrategy(oAuth2Provider: OAuth2Provider): OidcStrategy {
        return when (oAuth2Provider) {
            OAuth2Provider.KAKAO -> {
                applicationContext.getBean(KakaoOidcStrategy::class.java)
            }

            OAuth2Provider.GOOGLE -> TODO()
        }
    }

}
