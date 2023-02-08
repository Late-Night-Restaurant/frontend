package com.example.simya.src.model.story.create

data class CreateStoryDTO(
    var profileId: Long,
    var category: String,
    var signboardImageUrl: String,
    var houseName: String,
    var comment: String
)
