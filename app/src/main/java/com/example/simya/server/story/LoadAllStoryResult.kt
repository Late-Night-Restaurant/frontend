package com.example.simya.server.story

data class LoadAllStoryResult(
    var houseId: Long,
    var category: String,
    var signboardImageUrl: String,
    var houseName: String,
    var todayTopicTitle: String
)
