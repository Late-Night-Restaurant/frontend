package com.example.simya.server.story

data class CreateStoryResponse(
    var status: Int,
    var code: Int,
    var message: String,
    var result: CreateStoryResult
)
