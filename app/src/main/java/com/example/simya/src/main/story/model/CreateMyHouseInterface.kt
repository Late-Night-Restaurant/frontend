package com.example.simya.src.main.story.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.story.create.CreateStoryResponse

interface CreateMyHouseInterface {
    fun onPostCreateMyHouseSuccess(response: CreateStoryResponse)
    fun onPostCreateMyHouseFailure(response: BaseResponse)
    fun onPostCreateMyHouseDisconnect(message: String)
}