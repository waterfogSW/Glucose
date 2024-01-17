package com.waterfogsw.glucose.user.application.service.applicaiton

import com.waterfogsw.glucose.user.application.port.inbound.UserRegisterUseCase
import com.waterfogsw.glucose.user.application.service.domain.UserDomainService
import com.waterfogsw.glucose.user.application.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.user.domain.entity.User
import org.springframework.stereotype.Service

@Service
class UserRegisterApplicationService(
    private val userDomainService: UserDomainService,
    private val userSocialLoginInfoDomainService: UserSocialLoginInfoDomainService,
) : UserRegisterUseCase {

    override fun invoke(command: UserRegisterUseCase.Command): UserRegisterUseCase.Result {
        val user: User = userDomainService.create(
            name = command.name,
            email = command.email,
            picture = command.picture,
        )

        userSocialLoginInfoDomainService.create(
            userId = user.id,
            email = command.email,
            provider = command.provider,
        )

        return UserRegisterUseCase.Result.Success(userId = user.id)
    }

}
