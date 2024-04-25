package com.waterfogsw.glucose.application.chat.service

import com.waterfogsw.glucose.application.chat.port.inbound.SendChatMessage
import com.waterfogsw.glucose.domain.chat.entity.ChatMessage
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class SendChatMessageService: SendChatMessage {

    val logger = KotlinLogging.logger { }
    override fun sendMessage(
        chatRoomId: UUID,
        senderId: String,
        content: String,
    ): ChatMessage {
        logger.info { "sendMessage: chatRoomId=$chatRoomId, senderId=$senderId, content=$content" }
        // TODO: Implement the logic to send a chat message to message broker
        return ChatMessage.create(
            senderId = senderId,
            content = content,
        )
    }


}
