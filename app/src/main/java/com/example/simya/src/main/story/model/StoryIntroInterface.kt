package com.example.simya.src.main.story.model

import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse

interface StoryIntroInterface {
    fun onGetStoryDetailSuccess(response: InquiryStoryDetailResponse)
    fun onGetStoryDetailFailure(response: InquiryStoryDetailResponse)
    fun onGetStoryDetailDisconnect(message: String)
}