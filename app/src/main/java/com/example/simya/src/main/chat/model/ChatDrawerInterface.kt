package com.example.simya.src.main.chat.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.ChatProfileListResponse

interface ChatDrawerInterface {
    fun onGetChatProfileListSuccess(response: ChatProfileListResponse)
    fun onGetChatProfileListFailure(response: BaseResponse)
    fun onGetChatProfileListDisconnect(message: String)
}