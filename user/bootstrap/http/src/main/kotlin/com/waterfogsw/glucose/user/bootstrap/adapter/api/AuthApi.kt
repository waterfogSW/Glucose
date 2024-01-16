package com.waterfogsw.glucose.user.bootstrap.adapter.api

import com.waterfogsw.glucose.user.bootstrap.adapter.dto.RefreshLoginTokenResponse
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.SocialLoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

@Tag(name = "인증", description = "회원 인증 API")
interface AuthApi {

    @Operation(summary = "소셜 로그인 API - 회원 가입, 로그인, 토큰 발급")
    @GetMapping("/login/oauth2/code/{provider}")
    @ResponseStatus(HttpStatus.OK)
    fun socialLogin(
        @PathVariable provider: String,
        @RequestParam code: String
    ): SocialLoginResponse

    @Operation(summary = "로그인 토큰 갱신 API")
    @GetMapping("/login/refresh")
    @ResponseStatus(HttpStatus.OK)
    fun refreshLoginToken(
        @RequestParam refreshToken: String
    ): RefreshLoginTokenResponse

}