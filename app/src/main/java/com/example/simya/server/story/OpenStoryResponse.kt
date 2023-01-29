package com.example.simya.server.story

data class OpenStoryResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: OpenStoryResult
)
