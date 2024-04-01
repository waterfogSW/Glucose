package com.waterfogsw.glucose.domain.chat.entity

import com.waterfogsw.glucose.support.common.util.UuidCreator
import java.util.*

data class ChatMessage(
    val id: UUID = UuidCreator.create(),
    val type: Type,
    val content: String,
) {

    enum class Type {
        TEXT,
    }

    companion object {

        fun create(
            type: Type = Type.TEXT,
            content: String,
        ): ChatMessage {
            return ChatMessage(
                type = type,
                content = content,
            )
        }
    }

}
