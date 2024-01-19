package com.waterfogsw.glucose.support.common.vo

@JvmInline
value class Email(val value: String) {
    init {
        require(isValidEmail(value)) { "유효한 이메일 형식이 아닙니다." }
    }

    companion object {
        private val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    }

    private fun isValidEmail(value: String): Boolean {
        return emailRegex.matches(value)
    }
}
