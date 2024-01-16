package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.UserOAuthInfoRepository
import com.waterfogsw.glucose.user.application.port.UserRepository
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import org.springframework.stereotype.Service

@Service
class UserRegister(
    private val userRepository: UserRepository,
    private val userOAuthInfoRepository: UserOAuthInfoRepository,
) : UserRegisterUseCase {

    override fun invoke(command: UserRegisterUseCase.Command): UserRegisterUseCase.Result {
        val user: User = User.create(
            name = command.name,
            email = command.email,
            picture = command.picture,
        )
        userRepository.save(user)

        val userOAuthInfo = UserOAuthInfo.create(
            userId = user.id,
            email = user.email,
            provider = command.provider,
        )
        userOAuthInfoRepository.save(userOAuthInfo)

        return UserRegisterUseCase.Result.Success(userId = user.id)
    }

}
