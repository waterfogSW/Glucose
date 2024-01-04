package com.waterfogsw.glucose.user.infrastructure.client.api

import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenInfoResponse
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenRequest
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "KakaoOidcApi")
interface KakaoOidcApi {

    @PostMapping(
        path = ["\${oidc.providers.kakao.token-url}"],
        produces = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getToken(request: GetTokenRequest): GetTokenResponse

    @PostMapping(
        path = ["\${oidc.providers.kakao.token-info-url}"],
        produces = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTokenInfo(idToken: String): GetTokenInfoResponse

}
