package com.waterfogsw.glucose.domain.chat.entity

import com.waterfogsw.glucose.support.common.util.UuidCreator
import java.util.*

data class ChatMessage(
    val id: UUID = UuidCreator.create(),
    val type: Type,
    val content: String,
    val senderId: String,
) {

    enum class Type {
        TEXT,
    }

    companion object {

        fun create(
            type: Type = Type.TEXT,
            senderId: String,
            content: String,
        ): ChatMessage {
            return ChatMessage(
                type = type,
                content = content,
                senderId = senderId,
            )
        }
    }

}
