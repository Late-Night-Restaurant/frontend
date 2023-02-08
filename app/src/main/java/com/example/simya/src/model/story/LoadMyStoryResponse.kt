package com.example.simya.src.model.story

data class LoadMyStoryResponse(
    var status: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<LoadMyStoryResult>
)
