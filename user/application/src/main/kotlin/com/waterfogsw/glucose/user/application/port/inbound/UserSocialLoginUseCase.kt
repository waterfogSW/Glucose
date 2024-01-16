package com.waterfogsw.glucose.user.application.port.inbound

import com.waterfogsw.glucose.user.domain.enums.Provider
import java.util.*


fun interface UserSocialLoginUseCase {

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
