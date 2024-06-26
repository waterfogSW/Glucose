package com.waterfogsw.glucose.infrastructure.user.client.adapter.strategy

import com.waterfogsw.glucose.infrastructure.user.client.api.KakaoOidcApiMock
import com.waterfogsw.glucose.infrastructure.client.common.properties.OidcClientProperties
import com.waterfogsw.glucose.infrastructure.client.user.adapter.strategy.KakaoOidcStrategy
import com.waterfogsw.glucose.infrastructure.user.client.common.properties.OidcClientPropertiesFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class KakaoOidcStrategyTest : DescribeSpec({

    val kakaoOidcApiMock = KakaoOidcApiMock()
    val oidcClientProperties: OidcClientProperties =
        OidcClientPropertiesFixture.createKakaoProperties()

    describe("getToken()") {
        it("카카오 토큰 발급을 요청한다.") {
            // arrange
            val kakaoOidcStrategy = KakaoOidcStrategy(
                kakaoOidcApi = kakaoOidcApiMock,
                oidcClientProperties = oidcClientProperties,
            )
            val authorizationCode = "authorizationCode"

            // act
            val token: String = kakaoOidcStrategy.getToken(authorizationCode)

            // assert
            token shouldNotBe null
        }
    }

})
