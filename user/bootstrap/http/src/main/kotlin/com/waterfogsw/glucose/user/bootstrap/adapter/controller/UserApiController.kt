package com.waterfogsw.glucose.user.bootstrap.adapter.controller

import com.waterfogsw.glucose.user.application.port.inbound.RegisterUser
import com.waterfogsw.glucose.user.bootstrap.adapter.api.UserApi
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.UserRegisterRequest
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
