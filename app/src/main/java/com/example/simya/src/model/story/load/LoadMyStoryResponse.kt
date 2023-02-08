package com.example.simya.src.model.story.load

import com.example.simya.config.BaseResponse

data class LoadMyStoryResponse(
    var result: List<LoadMyStoryResult>
): BaseResponse()
