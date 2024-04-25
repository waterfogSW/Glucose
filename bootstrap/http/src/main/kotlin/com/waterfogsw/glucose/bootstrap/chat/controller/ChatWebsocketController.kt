package com.waterfogsw.glucose.bootstrap.chat.controller

import com.waterfogsw.glucose.application.chat.port.inbound.SendChatMessage
import com.waterfogsw.glucose.domain.chat.entity.ChatMessage
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class ChatWebsocketController(
    private val sendChatMessage: SendChatMessage,
) {

    @MessageMapping("/chat-room/{chatRoomId}")
    @SendTo("/topic/chat-room/{chatRoomId}")
    fun sendMessage(
        @DestinationVariable chatRoomId: UUID,
        @Header("senderId") senderId: String,
        @Payload message: String,
    ): ChatMessage {
        return sendChatMessage.sendMessage(
            chatRoomId = chatRoomId,
            senderId = senderId,
            content = message,
        )
    }

}
