package com.example.simya.src.model.story.load

import com.example.simya.config.BaseResponse

data class LoadAllStoryResponse(
    var status: Int,
    var result: List<LoadAllStoryResult>
): BaseResponse()
