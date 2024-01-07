package com.waterfogsw.glucose.user.infrastructure.client.mock

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.KakaoOidcApiDtoFixtures.getTokenInfoResponse
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.KakaoOidcApiDtoFixtures.getTokenResponse
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
                .withHeader("Content-Type", containing(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .withRequestBody(containing("client_id"))
                .withRequestBody(containing("redirect_uri"))
                .withRequestBody(containing("code"))
                .withRequestBody(containing("client_secret"))
                .withRequestBody(containing("grant_type"))
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
                .withHeader("Content-Type", containing(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .withRequestBody(containing("id_token"))
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(getTokenInfoResponse()))
                )

        )
    }


}
