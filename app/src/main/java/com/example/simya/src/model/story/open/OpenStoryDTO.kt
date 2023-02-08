package com.example.simya.src.model.story.open

import com.example.simya.src.model.story.topic.TopicRequestDTO

data class OpenStoryDTO(
    var houseId: Long,
    var capacity: Int,
    var topic: TopicRequestDTO
)
