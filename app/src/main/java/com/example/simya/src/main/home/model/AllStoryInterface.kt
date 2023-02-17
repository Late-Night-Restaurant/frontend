package com.example.simya.src.main.home.model

import com.example.simya.src.model.story.load.LoadAllStoryResponse

interface AllStoryInterface {
    fun onGetAllStorySuccess(response: LoadAllStoryResponse)
    fun onGetAllStoryFailure(response: LoadAllStoryResponse)
    fun onGetAllStoryDisconnect(message: String)
}