package com.example.simya.src.model.mypage.review

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class MyWriteReviewResponse(
    @SerializedName("result") val result: List<WriteReviewResult>
): BaseResponse()
