package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import com.waterfogsw.glucose.user.domain.vo.URL
import java.util.*

fun interface UserRegisterUseCase {

    fun invoke(command: Command): Result

    data class Command(
        val name: String,
        val email: Email,
        val picture: URL?,
        val provider: Provider,
    )

    sealed class Result {
        data class Success(
            val userId: UUID
        ) : Result()

        data class Failure(
            val throwable: Throwable
        ) : Result()
    }


}
