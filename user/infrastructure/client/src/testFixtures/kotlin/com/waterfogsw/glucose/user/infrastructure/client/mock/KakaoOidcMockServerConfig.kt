package com.waterfogsw.glucose.user.infrastructure.client.mock

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.waterfogsw.glucose.user.infrastructure.client.api.KakaoOidcApiDtoFixtures.getTokenInfoResponse
import com.waterfogsw.glucose.user.infrastructure.client.api.KakaoOidcApiDtoFixtures.getTokenResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


object KakaoOidcMockServerConfig {

    private val objectMapper = ObjectMapper()

    fun init() {
        setUpTokenRequestStub()
        setUpTokenInfoRequestStub()
    }

    private fun setUpTokenRequestStub() {
        val url = "/oauth/token"
        stubFor(
            post(urlEqualTo(url))
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(getTokenResponse()))
                )


        )
    }

    private fun setUpTokenInfoRequestStub() {
        val url = "/oauth/tokeninfo"
        stubFor(
            post(urlEqualTo(url))
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(getTokenInfoResponse()))
                )

        )
    }


}