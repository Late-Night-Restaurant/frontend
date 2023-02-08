package com.example.simya.src.model.story

data class CreateStoryResponse(
    var status: Int,
    var code: Int,
    var message: String,
    var result: CreateStoryResult
)
