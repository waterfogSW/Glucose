package com.waterfogsw.glucose.user.bootstrap.adapter.api

import com.waterfogsw.glucose.user.bootstrap.adapter.dto.UserRegisterRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@Tag(name = "유저", description = "유저 API")
@RequestMapping("/api/user", produces = ["application/json;charset=utf-8"])
interface UserApi {

    @Operation(summary = "회원가입 API")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun socialLogin(
        @RequestBody
        request: UserRegisterRequest
    )

}
