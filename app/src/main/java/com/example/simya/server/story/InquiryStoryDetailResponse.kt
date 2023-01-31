package com.example.simya.server.story

data class InquiryStoryDetailResponse(
    var status: Int?,
    var isSuccess: Boolean,
    var message: String,
    var result: InquiryStoryDetailResult?
)
