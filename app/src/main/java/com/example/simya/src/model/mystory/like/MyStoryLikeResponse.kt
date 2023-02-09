package com.example.simya.src.model.mystory.like

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class MyStoryLikeResponse(
    @SerializedName("result") val result: MyStoryLikeResult
): BaseResponse()
