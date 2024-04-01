package com.waterfogsw.glucose.application.chat.port.inbound

import java.util.*

interface SendChatMessage {

    fun sendTextMessage(
        chatRoomId: UUID,
        content: String,
    )

}
