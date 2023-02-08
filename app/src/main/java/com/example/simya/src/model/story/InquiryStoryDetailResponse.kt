package com.example.simya.src.model.story

data class InquiryStoryDetailResponse(
    var status: Int?,
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: InquiryStoryDetailResult?
)
