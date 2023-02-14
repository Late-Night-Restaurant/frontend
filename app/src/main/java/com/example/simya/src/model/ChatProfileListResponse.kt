package com.example.simya.src.model

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ChatProfileListResponse(
    @SerializedName("result") val result: List<UserDTO>
): BaseResponse()
