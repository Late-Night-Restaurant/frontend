package com.example.simya.server.main

data class LoadAllStoryResult(
    val houseId: Long,
    val category: String,
    val signboardImageUrl: String,
    val houseName: String,
    val todayTopicTitle: String
)
