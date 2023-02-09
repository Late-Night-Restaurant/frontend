package com.example.simya.src.model.story.create

import com.example.simya.config.BaseResponse

data class CreateStoryResponse(
    var status: Int,
    var result: CreateStoryResult
): BaseResponse()
