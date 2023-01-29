package com.example.simya.server.story

data class LoadMyStoryResponse(
    var status: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<LoadMyStoryResult>
)
