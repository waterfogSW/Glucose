package com.waterfogsw.glucose.user.infrastructure.client.api

import com.waterfogsw.glucose.user.infrastructure.client.ClientIntegrationTestApplication
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.KakaoOidcApiDto
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.KakaoOidcApiDtoFixtures.getTokenRequest
import com.waterfogsw.glucose.user.infrastructure.client.mock.KakaoOidcMockServerConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [ClientIntegrationTestApplication::class],
    properties = ["spring.config.location=classpath:application-client-test.yaml"],
)
@AutoConfigureWireMock(httpsPort = 443)
class KakaoOidcApiTest(
    private val kakaoOidcApi: KakaoOidcApi
) : DescribeSpec({

    beforeSpec {
        KakaoOidcMockServerConfig.init()
    }

    describe("getToken()") {
        it("카카오 인증서버에 토큰을 요청한다.") {
            // arrange
            val request: KakaoOidcApiDto.GetTokenRequest = getTokenRequest()

            // act
            val token: KakaoOidcApiDto.GetTokenResponse = kakaoOidcApi.getToken(request)

            // assert
            token shouldNotBe null
        }
    }

    describe("getTokenInfo()") {
        it("카카오 인증서버에 토큰 정보를 요청한다.") {
            // arrange
            val request: KakaoOidcApiDto.GetTokenInfoRequest = KakaoOidcApiDto.GetTokenInfoRequest(
                idToken = "test"
            )

            // act
            val tokenInfo: KakaoOidcApiDto.GetTokenInfoResponse = kakaoOidcApi.getTokenInfo(request)

            // assert
            tokenInfo shouldNotBe null
        }
    }

})
