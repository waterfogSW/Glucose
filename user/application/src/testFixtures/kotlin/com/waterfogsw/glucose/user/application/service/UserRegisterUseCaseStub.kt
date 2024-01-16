package com.waterfogsw.glucose.user.application.service

import com.waterfogsw.glucose.user.application.port.inbound.UserRegisterUseCase
import java.util.*

class UserRegisterUseCaseStub : UserRegisterUseCase {

    override fun invoke(command: UserRegisterUseCase.Command): UserRegisterUseCase.Result {
        return UserRegisterUseCase.Result.Success(userId = UUID.randomUUID())
    }


}
