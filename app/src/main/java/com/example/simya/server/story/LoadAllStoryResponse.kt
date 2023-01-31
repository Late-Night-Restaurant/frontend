package com.example.simya.server.story

data class LoadAllStoryResponse(
    var code: Int,
    var status: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<LoadAllStoryResult>
)
