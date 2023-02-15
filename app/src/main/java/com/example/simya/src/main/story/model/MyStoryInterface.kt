package com.example.simya.src.main.story.model

import com.example.simya.src.model.story.load.LoadMyStoryResponse

interface MyStoryInterface {
    fun onGetMyStorySuccess(response: LoadMyStoryResponse)
    fun onGetMyStoryFailure(response: LoadMyStoryResponse)
    fun onGetMyStoryDisconnect(message: String)
}