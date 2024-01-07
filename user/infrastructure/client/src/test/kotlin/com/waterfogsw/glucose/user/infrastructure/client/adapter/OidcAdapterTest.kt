package com.waterfogsw.glucose.user.infrastructure.client.adapter

import com.waterfogsw.glucose.user.application.port.OidcPort
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy.MockOidcStrategyFactory
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class OidcAdapterTest : DescribeSpec({

    val mockOidcStrategyFactory = MockOidcStrategyFactory()
    val stub = OidcAdapter(mockOidcStrategyFactory)

    describe("getUserInfo()") {
        it("소셜 로그인 제공자별 사용자 정보를 가져온다.") {
            // arrange
            val code = "authorizationCode"
            val provider = OAuth2Provider.GOOGLE

            // act
            val userInfo: OidcPort.UserInfo = stub.getUserInfo("authorizationCode", OAuth2Provider.GOOGLE)

            // assert
            userInfo.sub shouldBe "sub"
            userInfo.name shouldBe "name"
            userInfo.email shouldBe "email"
            userInfo.profileImage shouldBe "profileImage"
        }
    }

})
