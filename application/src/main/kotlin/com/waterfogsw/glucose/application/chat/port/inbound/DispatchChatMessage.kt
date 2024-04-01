package com.waterfogsw.glucose.application.chat.port.inbound

import com.waterfogsw.glucose.domain.chat.entity.ChatMessage

fun interface DispatchChatMessage {

    fun dispatchChatMessage(chatMessage: ChatMessage)

}
