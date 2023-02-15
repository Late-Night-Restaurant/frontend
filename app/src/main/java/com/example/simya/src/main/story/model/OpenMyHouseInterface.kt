package com.example.simya.src.main.story.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.story.create.CreateStoryResponse
import com.example.simya.src.model.story.open.OpenStoryResponse

interface OpenMyHouseInterface {
    fun onPatchCreateMyHouseSuccess(response: OpenStoryResponse)
    fun onPatchCreateMyHouseFailure(response: OpenStoryResponse)
    fun onPatchCreateMyHouseDisconnect(message: String)
}