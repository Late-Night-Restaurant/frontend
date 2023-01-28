package com.example.simya.server.profile

import android.provider.ContactsContract.CommonDataKinds.Nickname

data class ProfileResult(
    var profileId: Long,
    var nickname: String,
    var comment: String,
    var picture: String
)
