package com.example.simya.src.model.story.inquiry

import com.example.simya.config.BaseResponse

data class InquiryStoryDetailResponse(
    var status: Int?,
    var result: InquiryStoryDetailResult?
): BaseResponse()
