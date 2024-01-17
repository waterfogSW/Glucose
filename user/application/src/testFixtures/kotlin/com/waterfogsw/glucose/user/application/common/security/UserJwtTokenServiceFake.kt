package com.waterfogsw.glucose.user.application.common.security

import com.waterfogsw.glucose.common.jwt.util.JwtTokenProvider
import com.waterfogsw.glucose.common.jwt.vo.JwtClaims
import com.waterfogsw.glucose.user.application.common.security.jwt.UserJwtTokenType
import com.waterfogsw.glucose.user.application.common.security.jwt.service.UserJwtTokenService
import com.waterfogsw.glucose.user.domain.entity.User

class UserJwtTokenServiceFake : UserJwtTokenService {

    override fun createAccessToken(user: User): String {
        val claims = JwtClaims {
            customClaims {
                this["type"] = UserJwtTokenType.Access.value
            }
        }
        return JwtTokenProvider.createToken(claims)
    }


    override fun createRefreshToken(user: User): String {
        val claims = JwtClaims {
            customClaims {
                this["type"] = UserJwtTokenType.Refresh.value
            }
        }
        return JwtTokenProvider.createToken(claims)
    }
}
