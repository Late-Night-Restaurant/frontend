package com.example.simya.src.model.profile

data class ProfileResponse(
    var isSuccess: Boolean?,
    var code: Int?,
    var message: String?,
    var result: List<ProfileResult>?
)
