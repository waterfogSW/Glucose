package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.domain.entity.Provider
import java.util.*


fun interface SocialLoginUserUseCase {

    fun invoke(command: Command): Result

    data class Command(
        val authorizationCode: String,
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
