package com.example.simya.server.main

data class LoadAllStoryResponse(
    val status: Int,
    val code: Int,
    val message: String,
    val result: List<LoadAllStoryResult>?
)
