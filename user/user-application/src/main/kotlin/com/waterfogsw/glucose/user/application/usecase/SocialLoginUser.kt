package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.SocialLoginPort
import com.waterfogsw.glucose.user.application.port.UserRepository
import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.domain.entity.UserLoginInfo
import org.springframework.stereotype.Service

@Service
class SocialLoginUser(
    private val userRepository: UserRepository,
    private val socialLoginPort: SocialLoginPort,
    private val socialLoginInfoRepository: UserSocialLoginInfoRepository
) : SocialLoginUserUseCase {

    override fun invoke(command: SocialLoginUserUseCase.Command): SocialLoginUserUseCase.Result {
        val userInfo: SocialLoginPort.UserInfo = socialLoginPort.getUserInfo(
            authorizationCode = command.authorizationCode,
            provider = command.provider,
        )

        val userLoginInfo: UserLoginInfo? = socialLoginInfoRepository.findBySubAndProvider(
            sub = userInfo.sub,
            provider = command.provider
        )

        if (userLoginInfo == null) {
            val user: User = User.create(
                username = userInfo.name,
                email = userInfo.email,
                profileImage = userInfo.profileImage
            ).apply { userRepository.save(this) }

            UserLoginInfo.create(
                sub = userInfo.sub,
                userId = user.id,
                provider = command.provider
            ).apply { socialLoginInfoRepository.save(this) }

            return SocialLoginUserUseCase.Result.Success(userId = user.id)
        }

        return SocialLoginUserUseCase.Result.Success(userId = userLoginInfo.userId)
    }

}
