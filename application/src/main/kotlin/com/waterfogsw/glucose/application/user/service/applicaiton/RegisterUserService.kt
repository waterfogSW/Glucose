package com.waterfogsw.glucose.application.user.service.applicaiton

import com.waterfogsw.glucose.application.user.port.inbound.RegisterUser
import com.waterfogsw.glucose.application.user.service.domain.UserDomainService
import com.waterfogsw.glucose.application.user.service.domain.UserSocialLoginInfoDomainService
import com.waterfogsw.glucose.domain.user.entity.User
import org.springframework.stereotype.Service

@Service
class RegisterUserService(
    private val userDomainService: UserDomainService,
    private val userSocialLoginInfoDomainService: UserSocialLoginInfoDomainService,
) : RegisterUser {

    override fun invoke(command: RegisterUser.Command): RegisterUser.Result {
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

        return RegisterUser.Result.Success(userId = user.id)
    }

}
