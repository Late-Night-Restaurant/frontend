package com.example.simya.src.model.story.open

import com.example.simya.src.model.story.topic.TopicRequestDTO
import com.google.gson.annotations.SerializedName

data class OpenStoryDTO(
    @SerializedName("houseId")
    var houseId: Long,
    @SerializedName("capacity")
    var capacity: Int,
    @SerializedName("topic")
    var topic: TopicRequestDTO
)
