package com.example.simya.server.account

data class SignupDTO(
    var email: String,
    var password: String,
    var profile: com.example.simya.server.account.ProfileDTO
)


