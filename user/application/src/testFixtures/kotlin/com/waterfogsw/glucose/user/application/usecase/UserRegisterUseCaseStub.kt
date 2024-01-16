package com.waterfogsw.glucose.user.application.usecase

import java.util.*

class UserRegisterUseCaseStub : UserRegisterUseCase {

    override fun invoke(command: UserRegisterUseCase.Command): UserRegisterUseCase.Result {
        return UserRegisterUseCase.Result.Success(userId = UUID.randomUUID())
    }


}
