package com.example.simya.src.model.story.inquiry

import com.google.gson.annotations.SerializedName

data class InquiryStoryDetailMasterDTO(
    @SerializedName("profileId")
    var profileId: Long,
    @SerializedName("nickname")
    var nickname: String,
    @SerializedName("comment")
    var comment: String,
    @SerializedName("pictureUrl")
    var pictureUrl: String
)
