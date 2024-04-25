package com.waterfogsw.glucose.application.chat.port.inbound

import com.waterfogsw.glucose.domain.chat.entity.ChatMessage
import java.util.*

interface SendChatMessage {

    fun sendMessage(
        chatRoomId: UUID,
        senderId: String,
        content: String,
    ): ChatMessage

}
