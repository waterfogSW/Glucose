package com.waterfogsw.glucose.application.user.service.domain.exception

import com.waterfogsw.glucose.support.common.exception.CustomExceptionType

enum class UserException(override val code: String) : CustomExceptionType {
    USER_NOT_FOUND("USER_001"),
}
