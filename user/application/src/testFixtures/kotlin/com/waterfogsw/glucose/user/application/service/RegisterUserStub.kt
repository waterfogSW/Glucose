package com.waterfogsw.glucose.user.application.service

import com.waterfogsw.glucose.user.application.port.inbound.RegisterUser
import java.util.*

class RegisterUserStub : RegisterUser {

    override fun invoke(command: RegisterUser.Command): RegisterUser.Result {
        return RegisterUser.Result.Success(userId = UUID.randomUUID())
    }


}
