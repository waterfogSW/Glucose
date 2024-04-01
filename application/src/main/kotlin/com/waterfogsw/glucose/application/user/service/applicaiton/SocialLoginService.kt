package com.waterfogsw.glucose.application.user.service.applicaiton

import com.waterfogsw.glucose.application.user.common.security.jwt.service.UserJwtTokenService
import com.waterfogsw.glucose.application.user.port.inbound.SocialLogin
import com.waterfogsw.glucose.application.user.port.outbound.UserOidcPort
import com.waterfogsw.glucose.application.user.service.domain.UserDomainService
import com.waterfogsw.glucose.application.user.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.domain.user.entity.User
import com.waterfogsw.glucose.domain.user.entity.UserSocialLoginInfo
import org.springframework.stereotype.Service

@Service
class SocialLoginService(
    private val userOidcPort: UserOidcPort,
    private val userJwtTokenService: UserJwtTokenService,
    private val userDomainService: UserDomainService,
    private val userSocialLoginInfoDomainService: UserSocialLoginInfoDomainService,
) : SocialLogin {

    override fun invoke(command: SocialLogin.Command): SocialLogin.Result {
        val userInfo: UserOidcPort.UserInfo = userOidcPort.getUserInfo(
            authorizationCode = command.authorizationCode,
            provider = command.provider,
        )

        val userSocialLoginInfo: UserSocialLoginInfo =
            userSocialLoginInfoDomainService.findByEmail(email = userInfo.email)
                ?: return SocialLogin.Result.UserNotRegistered(
                    name = userInfo.name,
                    email = userInfo.email,
                    provider = command.provider,
                    picture = userInfo.picture,
                )

        val user: User = userDomainService.getById(userSocialLoginInfo.userId)

        val accessToken: String = userJwtTokenService.createAccessToken(user = user)
        val refreshToken: String = userJwtTokenService.createRefreshToken(user = user)

        return SocialLogin.Result.Success(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

}
