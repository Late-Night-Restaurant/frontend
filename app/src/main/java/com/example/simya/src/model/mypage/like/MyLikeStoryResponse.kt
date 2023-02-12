package com.example.simya.src.model.mypage.like

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class MyLikeStoryResponse(
    @SerializedName("result") val result: List<MyLikeStoryResult>
): BaseResponse()
