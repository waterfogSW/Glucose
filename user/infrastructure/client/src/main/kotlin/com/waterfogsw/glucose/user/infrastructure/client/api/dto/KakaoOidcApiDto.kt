package com.waterfogsw.glucose.user.infrastructure.client.api.dto

import feign.form.FormProperty

/**
 * 카카오 OIDC API DTO
 */
class KakaoOidcApiDto {
    /**
     * 토큰 발급 요청을 위한 DTO
     *
     * @property clientId 앱 REST API 키
     * @property clientSecret 토큰 발급 시, 보안을 강화하기 위해 추가 확인하는 코드
     * @property redirectUri 리다이렉트 URI
     * @property code 인가 코드
     * @property grantType 토큰 발급 타입 (authorization_code로 고정)
     *
     * @see <a href="https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token">토큰 발급 요청</a>
     * @see <a href="https://github.com/OpenFeign/feign-form/issues/79">feign-form issue 79 val -> var</a>
     */
    data class GetTokenRequest(
        @FormProperty("client_id")
        var clientId: String,
        @FormProperty("client_secret")
        var clientSecret: String,
        @FormProperty("redirect_uri")
        var redirectUri: String,
        @FormProperty("code")
        var code: String,
        @FormProperty("grant_type")
        var grantType: String = "authorization_code"
    )

    /**
     * 토큰 발급 응답을 위한 DTO
     * @property accessToken 액세스 토큰
     * @property tokenType 토큰 타입
     * @property refreshToken 리프레시 토큰
     * @property idToken ID 토큰
     * @property expiresIn 토큰 만료 시간
     * @property scope 토큰 범위
     * @property refreshTokenExpiresIn 리프레시 토큰 만료 시간
     *
     * @see <a href="https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token">토큰 발급 요청</a>
     */
    data class GetTokenResponse(
        val accessToken: String,
        val tokenType: String,
        val refreshToken: String,
        val idToken: String,
        val expiresIn: Int,
        val scope: String,
        val refreshTokenExpiresIn: Int,
    )

    /**
     * 토큰 정보 조회 요청을 위한 DTO
     * @property idToken ID 토큰
     * @see <a href="https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#oidc-get-id-token-info">토큰 정보 조회</a>
     */
    data class GetTokenInfoRequest(
        @FormProperty("id_token")
        var idToken: String,
    )

    /**
     * 토큰 정보 조회 응답을 위한 DTO
     * @property iss 토큰 발급자
     * @property aud 토큰 대상자
     * @property sub 토큰 제목
     * @property iat 토큰 발급 시간
     * @property exp 토큰 만료 시간
     * @property nickname 닉네임
     * @property picture 프로필 이미지 URL
     * @property email 이메일
     * @property authTime 인증 시간
     * @see <a href="https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#oidc-get-id-token-info">토큰 정보 조회</a>
     */
    data class GetTokenInfoResponse(
        val iss: String,
        val aud: String,
        val sub: String,
        val iat: Int,
        val exp: Int,
        val nickname: String,
        val picture: String,
        val email: String,
        val authTime: Int,
    )
}
