package com.waterfogsw.glucose.application.user.common.security.jwt.service.impl

import com.waterfogsw.glucose.support.jwt.util.JwtTokenProvider
import com.waterfogsw.glucose.support.jwt.vo.JwtClaims
import com.waterfogsw.glucose.application.user.common.security.jwt.UserJwtTokenType
import com.waterfogsw.glucose.application.user.common.security.jwt.property.UserJwtTokenConfigProperties
import com.waterfogsw.glucose.application.user.common.security.jwt.service.UserJwtTokenService
import com.waterfogsw.glucose.domain.user.entity.User
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class UserJwtTokenProvider(
    private val properties: UserJwtTokenConfigProperties
) : UserJwtTokenService {

    override fun createAccessToken(user: User): String {
        val claims = JwtClaims {
            registeredClaims {
                sub = user.id.toString()
                exp = Date.from(Instant.now().plusSeconds(properties.access.expireSeconds))
            }
            customClaims {
                this["type"] = UserJwtTokenType.Access.value
                this["name"] = user.name
                this["email"] = user.email.value
                this["picture"] = user.picture.toString()
            }
        }
        return JwtTokenProvider.createToken(claims, properties.access.secret)
    }

    override fun createRefreshToken(user: User): String {
        val claims = JwtClaims {
            registeredClaims {
                sub = user.id.toString()
                exp = Date.from(Instant.now().plusSeconds(properties.refresh.expireSeconds))
            }
            customClaims {
                this["type"] = UserJwtTokenType.Refresh.value
            }
        }
        return JwtTokenProvider.createToken(claims, properties.refresh.secret)
    }

}
