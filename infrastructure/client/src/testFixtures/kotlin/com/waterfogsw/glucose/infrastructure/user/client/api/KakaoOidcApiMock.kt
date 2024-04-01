package com.waterfogsw.glucose.infrastructure.user.client.api

import com.waterfogsw.glucose.infrastructure.client.user.api.KakaoOidcApi
import com.waterfogsw.glucose.infrastructure.client.user.api.dto.KakaoOidcApiDto

class KakaoOidcApiMock : KakaoOidcApi {

    override fun getToken(request: KakaoOidcApiDto.GetTokenRequest): KakaoOidcApiDto.GetTokenResponse {
        return KakaoOidcApiDto.GetTokenResponse(
            accessToken = "accessToken",
            tokenType = "tokenType",
            refreshToken = "refreshToken",
            expiresIn = 1000,
            scope = "scope",
            idToken = "idToken",
            refreshTokenExpiresIn = 1000,
        )
    }

    override fun getTokenInfo(request: KakaoOidcApiDto.GetTokenInfoRequest): KakaoOidcApiDto.GetTokenInfoResponse {
        return KakaoOidcApiDto.GetTokenInfoResponse(
            iss = "iss",
            sub = "sub",
            aud = "aud",
            exp = 1000,
            iat = 1000,
            nickname = "nickname",
            picture = "https://k.kakaocdn.net/dn/abcde/btqCDEFGHij/1.jpg",
            email = "test@naver.com",
            authTime = 1000,
        )
    }

}
