package com.waterfogsw.glucose.common.jwt.vo

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import java.time.Instant
import java.util.*

/**
 * Represents the claims of a JSON Web Token (JWT).
 *
 * @property registeredClaims The registered claims of the JWT.
 * @property customClaims The custom claims of the JWT.
 */
data class JwtClaims (
    val registeredClaims: RegisteredClaims,
    val customClaims: MutableMap<String, Any> = mutableMapOf()
) {


    /**
     * Represents the registered claims defined in a JSON Web Token (JWT).
     *
     * @property sub The subject claim identifying the principal that is the subject of the JWT
     * @property exp The expiration time claim that specifies the time on or after which the JWT must not be accepted for processing
     * @property iat The issued at claim that identifies the time at which the JWT was issued
     * @property nbf The not before claim that identifies the time before which the JWT must not be accepted for processing
     * @property iss The issuer claim that identifies the entity that issued the JWT
     * @property aud The audience claim that identifies the recipients for which the JWT is intended
     * @property jti The JWT ID claim that provides a unique identifier for the JWT
     */
    data class RegisteredClaims(
        val sub: String? = null,
        val exp: Date? = Date.from(Instant.now().plusSeconds(DEFAULT_EXPIRATION_TIME_SEC)),
        val iat: Date? = Date.from(Instant.now()),
        val nbf: Date? = Date.from(Instant.now()),
        val iss: String? = null,
        val aud: Set<String>? = null,
        val jti: String? = null,
    )

    val sub: String?
        get() = registeredClaims.sub
    val exp: Date?
        get() = registeredClaims.exp
    val iat: Date?
        get() = registeredClaims.iat
    val nbf: Date?
        get() = registeredClaims.nbf
    val iss: String?
        get() = registeredClaims.iss
    val aud: Set<String>?
        get() = registeredClaims.aud
    val jti: String?
        get() = registeredClaims.jti

    companion object {

        const val DEFAULT_EXPIRATION_TIME_SEC: Long = 60 * 60 // 1 hour

        /**
         * Converts a JSON Web Signature (JWS) containing claims to a JwtClaims object.
         *
         * @param claims The Jws object containing the claims.
         * @return The converted JwtClaims object.
         */
        fun from(claims: Jws<Claims>): JwtClaims {
            val registeredClaims: RegisteredClaims = RegisteredClaims(
                sub = claims.payload.subject,
                exp = claims.payload.expiration,
                iat = claims.payload.issuedAt,
                nbf = claims.payload.notBefore,
                iss = claims.payload.issuer,
                aud = claims.payload.audience,
                jti = claims.payload.id
            )
            val customClaims: MutableMap<String, Any> = mutableMapOf()
            claims.payload.forEach { key, value ->
                if (registeredClaims::class.members.none { it.name == key }) {
                    customClaims[key] = value
                }
            }
            return JwtClaims(registeredClaims, customClaims)
        }

    }

}
