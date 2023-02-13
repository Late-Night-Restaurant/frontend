package com.example.simya.src.model.mypage.review

import com.example.simya.src.model.story.HouseDTO
import com.example.simya.src.model.UserDTO
import com.google.gson.annotations.SerializedName

data class WriteReviewResult(
    @SerializedName("reviewedHouse") val reviewHouse: HouseDTO,
    @SerializedName("myReview") val myReview: MyReview,
){
    data class MyReview(
        @SerializedName("reviewersProfile") val reviewersProfile: UserDTO,
        @SerializedName("reviewId") val reviewId: Long,
        @SerializedName("rate") val rate: Int,
        @SerializedName("content") val content: String,

        )
}
