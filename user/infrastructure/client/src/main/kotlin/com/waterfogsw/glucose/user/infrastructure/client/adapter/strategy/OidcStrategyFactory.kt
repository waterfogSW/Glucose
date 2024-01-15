package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.domain.enums.Provider

interface OidcStrategyFactory {
    fun getStrategy(provider: Provider): OidcStrategy

}
