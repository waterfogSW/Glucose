package com.waterfogsw.glucose.bootstrap.chat.controller

import com.waterfogsw.glucose.application.chat.port.inbound.SendChatMessage
import org.springframework.stereotype.Controller

@Controller
class ChatWebsocketController (
    private val sendChatMessage: SendChatMessage
){



}
