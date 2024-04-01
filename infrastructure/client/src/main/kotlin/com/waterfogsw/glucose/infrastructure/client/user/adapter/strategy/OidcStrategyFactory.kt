package com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy

import com.waterfogsw.glucose.domain.user.enums.Provider

interface OidcStrategyFactory {
    fun getStrategy(provider: Provider): OidcStrategy

}
