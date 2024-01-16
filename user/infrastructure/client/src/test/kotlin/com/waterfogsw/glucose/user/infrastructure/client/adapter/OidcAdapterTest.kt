package com.waterfogsw.glucose.user.infrastructure.client.adapter

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.common.support.vo.URL
import com.waterfogsw.glucose.user.application.port.outbound.OidcPort
import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.infrastructure.client.adapter.strategy.MockOidcStrategyFactory
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class OidcAdapterTest : DescribeSpec({

    val mockOidcStrategyFactory = MockOidcStrategyFactory()
    val stub = OidcAdapter(mockOidcStrategyFactory)

    describe("getUserInfo()") {
        it("소셜 로그인 제공자별 사용자 정보를 가져온다.") {
            // arrange
            val code = "authorizationCode"
            val provider = Provider.GOOGLE

            // act
            val userInfo: OidcPort.UserInfo = stub.getUserInfo(code, provider)

            // assert
            userInfo.sub shouldBe "sub"
            userInfo.name shouldBe "name"
            userInfo.email shouldBe Email("test@test.com")
            userInfo.picture shouldBe URL("https://test.com/1.jpg")
        }
    }

})
