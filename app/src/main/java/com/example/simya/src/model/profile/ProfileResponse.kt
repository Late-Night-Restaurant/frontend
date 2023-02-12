package com.example.simya.src.model.profile

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("result") val result: List<ProfileDTO>
): BaseResponse()
