package com.waterfogsw.glucose.user.application.service.applicaiton

import com.waterfogsw.glucose.user.application.common.security.jwt.service.UserJwtTokenService
import com.waterfogsw.glucose.user.application.port.inbound.UserSocialLoginUseCase
import com.waterfogsw.glucose.user.application.port.outbound.OidcPort
import com.waterfogsw.glucose.user.application.service.domain.UserDomainService
import com.waterfogsw.glucose.user.application.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import org.springframework.stereotype.Service

@Service
class UserSocialLoginApplicationService(
    private val oidcPort: OidcPort,
    private val userJwtTokenService: UserJwtTokenService,
    private val userDomainService: UserDomainService,
    private val userSocialLoginInfoDomainService: UserSocialLoginInfoDomainService,
) : UserSocialLoginUseCase {

    override fun invoke(command: UserSocialLoginUseCase.Command): UserSocialLoginUseCase.Result {
        val userInfo: OidcPort.UserInfo = oidcPort.getUserInfo(
            authorizationCode = command.authorizationCode,
            provider = command.provider,
        )

        val userSocialLoginInfo: UserSocialLoginInfo =
            userSocialLoginInfoDomainService.findByEmail(email = userInfo.email)
                ?: return UserSocialLoginUseCase.Result.UserNotRegistered(
                    name = userInfo.name,
                    email = userInfo.email,
                    provider = command.provider,
                    picture = userInfo.picture,
                )

        val user: User = userDomainService.getById(userSocialLoginInfo.userId)

        val accessToken: String = userJwtTokenService.createAccessToken(user = user)
        val refreshToken: String = userJwtTokenService.createRefreshToken(user = user)

        return UserSocialLoginUseCase.Result.Success(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

}
