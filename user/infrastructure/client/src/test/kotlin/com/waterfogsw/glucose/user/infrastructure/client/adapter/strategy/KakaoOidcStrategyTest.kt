package com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy

import com.waterfogsw.glucose.user.infrastructure.client.api.KakaoOidcApiMock
import com.waterfogsw.glucose.user.infrastructure.client.common.properties.OidcClientProperties
import com.waterfogsw.glucose.user.infrastructure.client.common.properties.OidcClientPropertiesFixture
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

    describe("getTokenInfo()") {
        it("카카오 토큰 정보 받기를 요청한다.") {
            // arrange
            val kakaoOidcStrategy = KakaoOidcStrategy(
                kakaoOidcApi = kakaoOidcApiMock,
                oidcClientProperties = oidcClientProperties,
            )
            val token = "token"

            // act
            val tokenInfo: OidcStrategy.IdTokenInfo = kakaoOidcStrategy.getTokenInfo(token)

            // assert
            tokenInfo.sub shouldNotBe null
        }
    }


})
