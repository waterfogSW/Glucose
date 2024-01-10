package com.waterfogsw.glucose.common.jwt.util

import com.waterfogsw.glucose.common.jwt.vo.JwtClaims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import io.jsonwebtoken.security.SignatureException
import javax.crypto.SecretKey

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
        val key: SecretKey? = secret?.let { Keys.hmacShaKeyFor(it.toByteArray()) }

        return Jwts
            .builder()
            .subject(jwtClaims.sub)
            .expiration(jwtClaims.exp)
            .issuedAt(jwtClaims.iat)
            .notBefore(jwtClaims.nbf)
            .issuer(jwtClaims.iss)
            .audience()
            .apply { jwtClaims.aud?.forEach { add(it) } }
            .and()
            .claims(jwtClaims.customClaims)
            .apply { key?.let { signWith(it) } }
            .compact()
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
    ) {
        val key: SecretKey? = secret?.let { Keys.hmacShaKeyFor(it.toByteArray()) }

        runCatching {
            Jwts.parser()
                .apply { if (key == null) unsecured() else verifyWith(key) }
                .build()
                .parse(token)
        }.onFailure { exception -> handleException(exception) }

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
    ): JwtClaims {
        val key: SecretKey? = secret?.let { Keys.hmacShaKeyFor(it.toByteArray()) }
        return runCatching {
            Jwts
                .parser()
                .apply { if (key == null) unsecured() else verifyWith(key) }
                .build()
                .parseSignedClaims(token)
        }
            .mapCatching { claims -> JwtClaims.from(claims) }
            .onFailure { exception -> handleException(exception) }
            .getOrThrow()
    }

    private val exceptionHandlerMap = mapOf<Class<out Throwable>, (Throwable) -> Nothing>(
        ExpiredJwtException::class.java to { e ->
            throw com.waterfogsw.glucose.common.jwt.error.ExpiredJwtException(
                e.message
            )
        },
        MalformedJwtException::class.java to { e ->
            throw com.waterfogsw.glucose.common.jwt.error.MalformedJwtException(
                e.message
            )
        },
        SignatureException::class.java to { e ->
            throw com.waterfogsw.glucose.common.jwt.error.SignatureException(
                e.message
            )
        },
        SecurityException::class.java to { e ->
            throw com.waterfogsw.glucose.common.jwt.error.SecurityException(
                e.message
            )
        },
        Throwable::class.java to { throw RuntimeException("토큰 검증 과정에서 알 수 없는 오류가 발생했습니다.") }
    )

    /**
     * Handle exceptions during token validation
     */
    private fun handleException(e: Throwable) {
        exceptionHandlerMap[e::class.java]?.invoke(e)
    }

}
