package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.OidcPort
import com.waterfogsw.glucose.user.application.port.UserRepository
import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import org.springframework.stereotype.Service

@Service
class UserSocialLogin(
    private val userRepository: UserRepository,
    private val oidcPort: OidcPort,
    private val socialLoginInfoRepository: UserSocialLoginInfoRepository
) : UserSocialLoginUseCase {

    override fun invoke(command: UserSocialLoginUseCase.Command): UserSocialLoginUseCase.Result {
        val userInfo: OidcPort.UserInfo = oidcPort.getUserInfo(
            authorizationCode = command.authorizationCode,
            provider = command.provider,
        )

        val userOAuthInfo: UserOAuthInfo? = socialLoginInfoRepository.findByEmail(email = userInfo.email)

        if (userOAuthInfo == null) {
            val user: User = User.create(
                username = userInfo.name,
                email = userInfo.email,
                picture = userInfo.profileImage
            ).apply { userRepository.save(this) }

            UserOAuthInfo.create(
                email = userInfo.email,
                userId = user.id,
                provider = command.provider
            ).apply { socialLoginInfoRepository.save(this) }

            return UserSocialLoginUseCase.Result.Success(userId = user.id)
        }

        return UserSocialLoginUseCase.Result.Success(userId = userOAuthInfo.userId)
    }

}
