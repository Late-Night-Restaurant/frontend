package com.example.simya.server.profile

data class ProfileResponse(
    var isSuccess: Boolean?,
    var code: Int?,
    var message: String?,
    var result: List<ProfileResult>?
)
