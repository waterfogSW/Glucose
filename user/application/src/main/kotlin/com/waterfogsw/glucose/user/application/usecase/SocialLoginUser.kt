package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.OidcPort
import com.waterfogsw.glucose.user.application.port.UserRepository
import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import org.springframework.stereotype.Service

@Service
class SocialLoginUser(
    private val userRepository: UserRepository,
    private val oidcPort: OidcPort,
    private val socialLoginInfoRepository: UserSocialLoginInfoRepository
) : SocialLoginUserUseCase {

    override fun invoke(command: SocialLoginUserUseCase.Command): SocialLoginUserUseCase.Result {
        val userInfo: OidcPort.UserInfo = oidcPort.getUserInfo(
            authorizationCode = command.authorizationCode,
            oAuth2Provider = command.oAuth2Provider,
        )

        val userSocialLoginInfo: UserSocialLoginInfo? =
            socialLoginInfoRepository.findBySubAndProvider(
                sub = userInfo.sub,
                oAuth2Provider = command.oAuth2Provider
            )

        if (userSocialLoginInfo == null) {
            val user: User = User.create(
                username = userInfo.name,
                email = userInfo.email,
                profileImage = userInfo.profileImage
            ).apply { userRepository.save(this) }

            UserSocialLoginInfo.create(
                sub = userInfo.sub,
                userId = user.id,
                oAuth2Provider = command.oAuth2Provider
            ).apply { socialLoginInfoRepository.save(this) }

            return SocialLoginUserUseCase.Result.Success(userId = user.id)
        }

        return SocialLoginUserUseCase.Result.Success(userId = userSocialLoginInfo.userId)
    }

}
