package com.waterfogsw.glucose.common.jwt.vo

import io.jsonwebtoken.Jwt
import java.time.Instant
import java.util.*

fun <H : io.jsonwebtoken.Header, P> Jwt<H, P>.getClaims(): JwtClaims {
    val payloadMap: Map<*, *> = this.payload as Map<*, *>
    val registeredClaims = JwtClaims.RegisteredClaims(
        sub = payloadMap["sub"] as String?,
        exp = payloadMap["exp"]?.let { Date.from(Instant.ofEpochMilli(it as Long)) },
        iat = payloadMap["iat"]?.let { Date.from(Instant.ofEpochMilli(it as Long)) },
        nbf = payloadMap["nbf"]?.let { Date.from(Instant.ofEpochMilli(it as Long)) },
        iss = payloadMap["iss"] as String?,
        aud = payloadMap["aud"]?.let { (it as Set<*>).map { content -> content as String }.toSet() },
        jti = payloadMap["jti"] as String?,
    )

    val customClaims = payloadMap
        .filterKeys { key -> key !in registeredClaims::class.members }
        .mapKeys { (key, _) -> key as String }
        .mapValues { (_, value) -> value as Any }


    return JwtClaims {
        registeredClaims {
            sub = registeredClaims.sub
            exp = registeredClaims.exp
            iat = registeredClaims.iat
            nbf = registeredClaims.nbf
            iss = registeredClaims.iss
            aud = registeredClaims.aud
            jti = registeredClaims.jti
        }

        customClaims {
            customClaims.forEach { (key, value) ->
                this[key] = value
            }
        }
    }
}
