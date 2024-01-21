package com.waterfogsw.glucose.user.bootstrap.adapter.controller

import com.waterfogsw.glucose.user.application.port.inbound.UserRegisterUseCase
import com.waterfogsw.glucose.user.bootstrap.adapter.api.UserApi
import com.waterfogsw.glucose.user.bootstrap.adapter.dto.UserRegisterRequest
import org.springframework.web.bind.annotation.RestController

@RestController
class UserApiController(
    private val userRegisterUseCase: UserRegisterUseCase
) : UserApi {

    override fun socialLogin(request: UserRegisterRequest) {
        userRegisterUseCase.invoke(
            UserRegisterUseCase.Command(
                name = request.name,
                email = request.email,
                picture = request.picture,
                provider = request.provider,
            )
        )
    }

}
