package com.waterfogsw.glucose.bootstrap.user.api

import com.waterfogsw.glucose.bootstrap.user.dto.RefreshLoginTokenResponse
import com.waterfogsw.glucose.bootstrap.user.dto.SocialLoginResponse
import com.waterfogsw.glucose.domain.user.enums.Provider
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "인증", description = "회원 인증 API")
@RequestMapping("/api/auth", produces = ["application/json;charset=utf-8"])
interface AuthApi {

    @Operation(summary = "소셜 로그인 API - 로그인 토큰 발급")
    @GetMapping("/login/oauth2/code/{provider}")
    @ResponseStatus(HttpStatus.OK)
    fun socialLogin(
        @PathVariable provider: Provider,
        @RequestParam code: String
    ): SocialLoginResponse

    @Operation(summary = "로그인 토큰 갱신 API")
    @GetMapping("/login/refresh")
    @ResponseStatus(HttpStatus.OK)
    fun refreshLoginToken(
        @RequestParam refreshToken: String
    ): RefreshLoginTokenResponse

}
