package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider

interface OidcStrategyFactory {
    fun getStrategy(oAuth2Provider: OAuth2Provider): OidcStrategy

}
