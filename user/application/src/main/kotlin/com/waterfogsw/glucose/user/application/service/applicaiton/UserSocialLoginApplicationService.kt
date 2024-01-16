package com.waterfogsw.glucose.user.application.service.applicaiton

import com.waterfogsw.glucose.user.application.port.inbound.UserRegisterUseCase
import com.waterfogsw.glucose.user.application.port.inbound.UserSocialLoginUseCase
import com.waterfogsw.glucose.user.application.port.outbound.OidcPort
import com.waterfogsw.glucose.user.application.port.outbound.UserSocialLoginInfoRepository
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import org.springframework.stereotype.Service

@Service
class UserSocialLoginApplicationService(
    private val oidcPort: OidcPort,
    private val userSocialLoginInfoRepository: UserSocialLoginInfoRepository,
    private val userRegisterUseCase: UserRegisterUseCase,
) : UserSocialLoginUseCase {

    override fun invoke(command: UserSocialLoginUseCase.Command): UserSocialLoginUseCase.Result {
        val userInfo: OidcPort.UserInfo = oidcPort.getUserInfo(
            authorizationCode = command.authorizationCode,
            provider = command.provider,
        )

        val userSocialLoginInfo: UserSocialLoginInfo? =
            userSocialLoginInfoRepository.findByEmail(email = userInfo.email)

        if (userSocialLoginInfo == null) {
            val registerResult: UserRegisterUseCase.Result = userRegisterUseCase.invoke(
                UserRegisterUseCase.Command(
                    name = userInfo.name,
                    email = userInfo.email,
                    picture = userInfo.picture,
                    provider = command.provider,
                )
            )

            return when (registerResult) {
                is UserRegisterUseCase.Result.Success -> {
                    UserSocialLoginUseCase.Result.Success(userId = registerResult.userId)
                }

                is UserRegisterUseCase.Result.Failure -> {
                    UserSocialLoginUseCase.Result.Failure(throwable = registerResult.throwable)
                }
            }

        }

        return UserSocialLoginUseCase.Result.Success(userId = userSocialLoginInfo.userId)
    }

}
