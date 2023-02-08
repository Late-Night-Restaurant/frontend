package com.example.simya.src.model.story

data class CreateStoryResult(
    var houseId: Int,
    var category: String,
    var signboardImageUrl: String,
    var houseName: String,
    var comment: String
)
