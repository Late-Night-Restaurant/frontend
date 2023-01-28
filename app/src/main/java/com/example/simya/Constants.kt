package com.example.simya

object Constants {

    const val DEFAULT = "Default"
    const val CHAT_MASTER_CODE = 1
    const val CHAT_GUEST_CODE = 2

    const val SORT_RECENT = 11
    const val SORT_LONG = 12
    const val SORT_LIKE = 13

    const val YES = 20
    const val NO = 21

    const val OK = 200

    // HTTP STATUS CODE
    const val USERS_NOT_FOUND = 400 //가입하지 않은 이메일을 입력한 경우
    const val REQUEST_ERROR = 400 // 올바른 형식이 아닌 경우
    const val USERS_INVALID_ACCESS = 401 // 인가 접근 경로가 잘못된 경우
    const val USERS_NOT_AUTHORIZED = 401 // 인가 요청에 실패한 경우
    const val FAILED_TO_LOGIN = 404 // 로그인에 실패한 경우
    const val BANNED_USER_IN_LOGIN = 404 // 비활성화된 유저가 접근한 경우
    const val FAILED_TO_JWT = 500 // 토큰 발급에 실패한 경우
    const val FAILED_JWT_IN_ACCESS = 500 // 토근을 헤더에 넣지 못한 경우



    // INTENT STRING CODE
    const val BORDER_MAIN_MENU ="borderMainMenu"
    const val PROFILE_ID = "profileId"
}