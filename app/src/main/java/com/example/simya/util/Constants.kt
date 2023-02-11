package com.example.simya.util

object Constants {
    const val UNKNOWN = 0
    const val CHAT_MASTER_CODE = 1
    const val CHAT_GUEST_CODE = 2
    const val CHAT_SELF = 3
    const val CHAT_OTHERS = 4
    const val CHAT_NOTIFY = 5

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
    const val POST_FAIL_USER = 500
    const val FAILED_JWT_IN_ACCESS = 500 // 토근을 헤더에 넣지 못한 경우


    // STRING CODE
    const val DEFAULT = "Default"
    const val BORDER_TITLE = "title"
    const val BORDER_MAIN_MENU = "borderMainMenu"
    const val PROFILE_ID = "profileId"
    const val MAIN_MENU_LOVE = "사랑"
    const val MAIN_MENU_FAMILY = "가족"
    const val MAIN_MENU_RELATIONSHIP = "인간관계"
    const val MAIN_MENU_STRESS = "스트레스"
    const val MAIN_MENU_HOBBY = "취미"
    const val MAIN_MENU_CULTURE = "문화 생활"
    const val HOUSE_ID = "houseId"
    const val NICK_NAME = "nickname"
    const val COMMENT = "comment"
    const val EMAIL_VALIDATION = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val NICKNAME_VALIDATION = "^[가-힣]{1,8}$"
    const val COMMENT_VALIDATION = "^.{1,24}$"
    const val PW_VALIDATION =  """^[0-9a-zA-Z!@#$%^+\-=]*$"""

    const val ERROR_STRING_INPUT ="입력값을 확인해주세요"
    const val ERROR_STRING_DUPLICATE = "중복된 이메일입니다."
    const val ERROR_STRING_FAILED_SIGN_UP = "회원가입에 실패했습니다."
}