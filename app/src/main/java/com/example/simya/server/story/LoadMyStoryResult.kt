package com.example.simya.server.story

data class LoadMyStoryResult(
    var houseId: Long,
    var category: String,
    var signboardImageUrl: String,
    var houseName: String,
    var comment: String
)
