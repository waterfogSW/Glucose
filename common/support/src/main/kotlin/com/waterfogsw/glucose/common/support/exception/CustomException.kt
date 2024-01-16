package com.waterfogsw.glucose.common.support.exception

class CustomException(
    val type: CustomExceptionType,
    override val message: String
) : RuntimeException(message)
