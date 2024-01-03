package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import java.util.*


fun interface SocialLoginUserUseCase {

    fun invoke(command: Command): Result

    data class Command(
        val authorizationCode: String,
        val oAuth2Provider: OAuth2Provider,
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
