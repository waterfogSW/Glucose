package com.waterfogsw.glucose.application.chat.service

import com.waterfogsw.glucose.application.chat.port.inbound.SendChatMessage
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class SendChatMessageService : SendChatMessage {

    val logger = KotlinLogging.logger { }

    override fun sendTextMessage(
        chatRoomId: UUID,
        content: String,
    ) {
        logger.info { "Sending message to chat room $chatRoomId: $content" }
    }

}
