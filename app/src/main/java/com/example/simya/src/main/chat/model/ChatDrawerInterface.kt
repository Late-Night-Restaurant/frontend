package com.example.simya.src.main.chat.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.ChatProfileListResponse
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import com.example.simya.src.model.story.load.LoadMyStoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ChatDrawerInterface {
    fun onPostLoginSubmitSuccess(response: ChatProfileListResponse)
    fun onPostLoginSubmitFailure(response: BaseResponse)
}