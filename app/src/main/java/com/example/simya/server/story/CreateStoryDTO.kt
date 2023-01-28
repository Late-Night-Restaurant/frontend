package com.example.simya.server.story

data class CreateStoryDTO(
    var profileId: Long,
    var category: String,
    var signboardImageUrl: String,
    var houseName: String,
    var comment: String
)
