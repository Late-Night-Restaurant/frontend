package com.example.simya.server.account

data class SignupResponse(
    var isSuccess: Boolean?,
    var code: Int?,
    var message: String?,
    var result: com.example.simya.server.account.SignupResult?,
){
    fun getEmail(): String{
        return result!!.email
    }
    fun getProfile(): com.example.simya.server.account.ProfileDTO {
        return result!!.profile
    }
}
