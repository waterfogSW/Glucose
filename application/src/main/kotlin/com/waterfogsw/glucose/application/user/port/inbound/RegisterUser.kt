package com.waterfogsw.glucose.application.user.port.inbound

import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.domain.user.enums.Provider
import java.util.*

fun interface RegisterUser {

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
