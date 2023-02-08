package com.example.simya.src.model.story

data class OpenStoryResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: OpenStoryResult
)
