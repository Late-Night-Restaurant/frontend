package com.example.simya.src.model.story.create

data class CreateStoryResponse(
    var status: Int,
    var code: Int,
    var message: String,
    var result: CreateStoryResult
)
