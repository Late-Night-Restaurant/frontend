package com.example.simya.src.model


import com.example.simya.config.BaseResponse
import retrofit2.Call
import retrofit2.http.*
import com.example.simya.src.model.account.AccountDTO
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.src.model.profile.ProfileResponse
import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import com.example.simya.src.model.mypage.review.MyModifyReviewResponse
import com.example.simya.src.model.mypage.review.MyWriteReviewResponse
import com.example.simya.src.model.profile.MyProfileResponse
import com.example.simya.src.model.story.*
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
import com.example.simya.src.model.story.load.LoadAllStoryResponse
import com.example.simya.src.model.story.load.LoadMyStoryResponse
import com.example.simya.src.model.story.open.OpenStoryDTO
import com.example.simya.src.model.story.open.OpenStoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


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
