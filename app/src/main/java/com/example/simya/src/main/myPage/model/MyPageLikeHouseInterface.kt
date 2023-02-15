package com.example.simya.src.main.myPage.model

import com.example.simya.src.model.mypage.like.MyLikeStoryResponse

interface MyPageLikeHouseInterface {
    fun onGetMyLikeHouseSuccess(response:MyLikeStoryResponse)
    fun onGetMyLikeHouseFailure(response:MyLikeStoryResponse)
    fun onGetMyLikeHouseDisconnect(message: String)
}