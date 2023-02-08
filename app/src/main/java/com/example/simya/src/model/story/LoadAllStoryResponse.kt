package com.example.simya.src.model.story

data class LoadAllStoryResponse(
    var code: Int,
    var status: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<LoadAllStoryResult>
)
