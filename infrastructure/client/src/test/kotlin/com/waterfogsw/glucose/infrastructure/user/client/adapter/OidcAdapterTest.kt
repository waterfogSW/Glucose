package com.waterfogsw.glucose.infrastructure.user.client.adapter

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.application.user.port.outbound.UserOidcPort
import com.waterfogsw.glucose.infrastructure.client.user.adapter.UserOidcAdapter
import com.waterfogsw.glucose.domain.user.enums.Provider
import com.waterfogsw.glucose.infrastructure.user.client.adapter.strategy.MockOidcStrategyFactory
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class OidcAdapterTest : DescribeSpec({

    val mockOidcStrategyFactory = MockOidcStrategyFactory()
    val stub = UserOidcAdapter(mockOidcStrategyFactory)

    describe("getUserInfo()") {
        it("소셜 로그인 제공자별 사용자 정보를 가져온다.") {
            // arrange
            val code = "authorizationCode"
            val provider = Provider.GOOGLE

            // act
            val userInfo: UserOidcPort.UserInfo = stub.getUserInfo(code, provider)

            // assert
            userInfo.sub shouldBe "sub"
            userInfo.name shouldBe "name"
            userInfo.email shouldBe Email("test@test.com")
            userInfo.picture shouldBe URL("https://test.com/1.jpg")
        }
    }

})
