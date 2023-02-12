package com.example.simya.src.model.mypage.review

import com.google.gson.annotations.SerializedName

data class MyReviewDTO(
    @SerializedName("rate") val rate: Int,
    @SerializedName("content") val content: String
)
