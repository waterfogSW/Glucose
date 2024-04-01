package com.waterfogsw.glucose.application.chat.port.outbound

import com.waterfogsw.glucose.domain.chat.entity.ChatMessage

interface ChatMessageRepository {

    fun save(chatMessage: ChatMessage)

}
