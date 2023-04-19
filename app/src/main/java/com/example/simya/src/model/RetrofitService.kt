package com.example.simya.src.model


import com.example.simya.config.BaseResponse
import retrofit2.Call
import retrofit2.http.*
import com.example.simya.src.model.mypage.review.MyModifyReviewResponse
import com.example.simya.src.model.mypage.review.MyWriteReviewResponse
import com.example.simya.src.model.story.*
import retrofit2.http.GET
import retrofit2.http.Headers


interface RetrofitService {


    // 현재 프로필로 쓴 모든 리뷰 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/review/my")
    fun getMyWriteReview(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
    ): Call<MyWriteReviewResponse>

    // 내가 쓴 리뷰 수정하기
    @Headers("Content-Type: application/json")
    @PATCH("/simya/review/{reviewId}")
    fun modifyMyWriteReview(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("reviewId") reviewId: Long
    ): Call<MyModifyReviewResponse>

    // 내가 쓴 리뷰 삭제하기
    @Headers("Content-Type: application/json")
    @DELETE("/simya/review/{reviewId}")
    fun deleteMyWriteReview(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("reviewId") reviewId: Long
    ): Call<BaseResponse>
}
