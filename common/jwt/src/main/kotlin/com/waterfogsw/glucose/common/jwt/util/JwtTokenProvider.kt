package com.waterfogsw.glucose.common.jwt.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.waterfogsw.glucose.common.jwt.error.JwtExpiredException
import com.waterfogsw.glucose.common.jwt.error.JwtVerificationException
import com.waterfogsw.glucose.common.jwt.vo.JwtClaims

/**
 * Provides utility functions for creating and validating JWT tokens.
 */
object JwtTokenProvider {

    /**
     * Creates a JWT token with the specified subject, claims, secret, and expiration time.
     * The secret parameter is now nullable.
     *
     * @param secret The secret key to sign the token. If null, the token is considered unsecured.
     * @return The generated JWT token.
     */
    fun createToken(
        jwtClaims: JwtClaims,
        secret: String? = null,
    ): String {
        return JWT
            .create()
            .withJWTId(jwtClaims.jti)
            .withSubject(jwtClaims.sub)
            .withIssuer(jwtClaims.iss)
            .withAudience(*jwtClaims.aud?.toTypedArray() ?: arrayOf())
            .withIssuedAt(jwtClaims.iat)
            .withNotBefore(jwtClaims.nbf)
            .withExpiresAt(jwtClaims.exp)
            .withPayload(jwtClaims.customClaims)
            .let {
                if (secret == null) {
                    it.sign(Algorithm.none())
                } else {
                    it.sign(Algorithm.HMAC256(secret))
                }
            }
    }

    /**
     * Validates a JSON Web Token (JWT) using a provided secret key. If no secret key is provided, the token is considered unsecured.
     *
     * @param token The JWT token to validate.
     * @param secret The secret key used to sign the token. If null, the token is considered unsecured.
     */
    fun validateToken(
        token: String,
        secret: String? = null,
    ): Throwable? {
        val algorithm: Algorithm = getAlgorithm(secret)
        return runCatching { JWT.require(algorithm).build().verify(token) }
            .onFailure { exception -> handleException(exception) }
            .exceptionOrNull()
    }


    /**
     * Retrieves the claims from a JWT token using the specified token and secret.
     * The secret parameter is now nullable.
     *
     * @param token The JWT token from which to retrieve the claims.
     * @param secret The secret key used to sign the token. If null, the token is considered unsecured.
     * @return A map of the claims from the JWT token.
     */
    fun getClaims(
        token: String,
        secret: String? = null,
    ): Result<JwtClaims> {
        val algorithm = getAlgorithm(secret)
        return runCatching { JWT.require(algorithm).build().verify(token) }
            .mapCatching { claims -> JwtClaims.from(claims) }
            .onFailure { exception -> handleException(exception) }
    }

    private val exceptionHandlerList = listOf<Pair<Class<out Throwable>, (Throwable) -> Nothing>>(
        TokenExpiredException::class.java to { throw JwtExpiredException("토큰이 만료되었습니다.") },
        Throwable::class.java to { e -> throw JwtVerificationException("토큰 유효성 검사에 실패했습니다. message=${e.message}") }
    )

    private fun getAlgorithm(secret: String?): Algorithm {
        return if (secret == null) {
            Algorithm.none()
        } else {
            Algorithm.HMAC256(secret)
        }
    }

    /**
     * Handle exceptions during token validation
     */
    private fun handleException(ex: Throwable): Nothing {
        exceptionHandlerList.first { it.first.isAssignableFrom(ex::class.java) }.second.invoke(ex)
    }

}
