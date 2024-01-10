package com.waterfogsw.glucose.common.jwt.util

import com.auth0.jwk.Jwk
import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.waterfogsw.glucose.common.jwt.error.JwtExpiredException
import com.waterfogsw.glucose.common.jwt.error.JwtVerificationException
import com.waterfogsw.glucose.common.jwt.vo.JwtClaims
import java.security.interfaces.RSAPublicKey
import java.util.concurrent.TimeUnit

/**
 * JwtTokenProvider class provides utility methods to handle JSON Web Tokens (JWTs).
 *
 * The class provides methods to create, validate, and retrieve claims from a JWT.
 * It can generate a JWT token with the specified subject, claims, secret, and expiration time.
 * The secret can be nullable, and if null, the token is considered unsecured.
 * The class also provides a method to validate a JWT token using a provided secret key.
 * If no secret key is provided, the token is considered unsecured.
 * It also has a method to retrieve the claims from a JWT token using the specified token and secret.
 * The secret can be nullable, and if null, the token is considered unsecured.
 */
object JwtTokenProvider {

    /**
     * Creates a JSON Web Token (JWT) using the provided JWT claims and secret key.
     *
     * @param jwtClaims The claims for the JWT.
     * @param secret The secret key used to sign the JWT. If null, the JWT is considered unsecured.
     * @return Generated JWT as a string.
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

    fun verifyToken(
        token: String,
        secret: String? = null,
    ): Result<JwtClaims> {
        val algorithm: Algorithm = getAlgorithm(secret)
        return verifyToken(token, algorithm)
    }

    /**
     * Verifies the given token with the JWKS (JSON Web Key Set) retrieved from the specified domain.
     *
     * @param token The token to be verified.
     * @param domain The domain from which to retrieve the JWKS.
     * @return A Result object that encapsulates the result of the verification. On success, it contains JwtClaims,
     * which represents the claims of the JSON Web Token. On failure, it contains the corresponding exception.
     */
    fun verifyTokenWithJwks(
        token: String,
        domain: String,
    ): Result<JwtClaims> {
        val provider = JwkProviderBuilder(domain)
            .cached(10, 60 * 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build()
        val jwt = JWT.decode(token)
        val jwk: Jwk = provider[jwt.keyId]
        val algorithm = Algorithm.RSA256(jwk.publicKey as RSAPublicKey, null)
        return verifyToken(token, algorithm)
    }

    /**
     * Verifies the given token with the provided algorithm.
     *
     * @param token The token to be verified.
     * @param algorithm The algorithm used to verify the token.
     * @return A Result object that encapsulates the result of the verification. On success, it contains JwtClaims,
     * which represents the claims of the JSON Web Token. On failure, it contains the corresponding exception.
     */

    private fun verifyToken(
        token: String,
        algorithm: Algorithm,
    ): Result<JwtClaims> {
        return runCatching { JWT.require(algorithm).build().verify(token) }
            .mapCatching { claims -> JwtClaims.from(claims) }
            .onFailure { exception -> handleException(exception) }
    }

    /**
     * Retrieve the algorithm to be used for JWT verification based on the provided secret key.
     *
     * @param secret The secret key used to verify the token's signature. If null, the token is considered unsecured.
     * @return The algorithm to be used for JWT verification.
     */
    private fun getAlgorithm(secret: String?): Algorithm {
        return if (secret == null) {
            Algorithm.none()
        } else {
            Algorithm.HMAC256(secret)
        }
    }

    /**
     * Handles an exception by invoking the appropriate exception handler based on the type of exception.
     *
     * @param ex The Throwable object representing the exception.
     * @return This method does not return a value. It throws an exception of type Nothing.
     * @throws NoSuchElementException if there is no matching exception handler for the given exception.
     */
    private fun handleException(ex: Throwable): Nothing {
        exceptionHandlerList.first { it.first.isAssignableFrom(ex::class.java) }.second.invoke(ex)
    }

    private val exceptionHandlerList = listOf<Pair<Class<out Throwable>, (Throwable) -> Nothing>>(
        TokenExpiredException::class.java to { throw JwtExpiredException("토큰이 만료되었습니다.") },
        Throwable::class.java to { e -> throw JwtVerificationException("토큰 유효성 검사에 실패했습니다. message=${e.message}") }
    )

}
