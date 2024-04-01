package com.waterfogsw.glucose.bootstrap.user.controller

import com.waterfogsw.glucose.application.user.port.inbound.RegisterUser
import com.waterfogsw.glucose.bootstrap.user.api.UserApi
import com.waterfogsw.glucose.bootstrap.user.dto.UserRegisterRequest
import org.springframework.web.bind.annotation.RestController

@RestController
class UserApiController(
    private val registerUser: RegisterUser
) : UserApi {

    override fun socialLogin(request: UserRegisterRequest) {
        registerUser.invoke(
            RegisterUser.Command(
                name = request.name,
                email = request.email,
                picture = request.picture,
                provider = request.provider,
            )
        )
    }

}
