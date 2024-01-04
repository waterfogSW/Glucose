package com.waterfogsw.glucose.user.infrastructure.client.api

import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenInfoResponse
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenRequest
import com.waterfogsw.glucose.user.infrastructure.client.api.dto.GetTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "KakaoOidcApi", url = "\${oidc.providers.kakao.base-url}")
interface KakaoOidcApi {

    @PostMapping(
        value = ["\${oidc.providers.kakao.token-endpoint"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
    )
    fun getToken(request: GetTokenRequest): GetTokenResponse

    @PostMapping(
        value = ["\${oidc.providers.kakao.token-info-endpoint}"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
    )
    fun getTokenInfo(idToken: String): GetTokenInfoResponse

}
