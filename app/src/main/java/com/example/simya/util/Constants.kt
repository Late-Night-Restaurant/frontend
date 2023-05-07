
package com.example.simya.util

object Constants {
    // URL
//    const val BASE_URL = "http://www.simya.kro.kr:8080/"
//    const val SOCKET_URL = "ws://www.simya.kro.kr:8080/simya/ws-stomp/websocket"
    const val BASE_URL = "http://10.0.2.2:8080"
    const val SOCKET_URL = "ws://10.0.2.2:8080/simya/ws-stomp/websocket"

    //S3
    const val S3_URL = "https://simya-s3-bucket.s3.ap-northeast-2.amazonaws.com/"

    // TOKEN
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"

    // USER DATA
    const val USER_ID = "ID"
    const val USER_PW = "PW"
    const val USER_NICKNAME = "NICK_NAME"
    const val USER_PROFILE_ID = "PROFILE_ID"
    const val USER_PROFILE_IMAGE = "PROFILE_IMAGE"
    const val USER_PROFILE_COMMENT = "PROFILE_COMMENT"

    // CHAT
    const val UNKNOWN = 0
    const val CHAT_MASTER_CODE = 1
    const val CHAT_GUEST_CODE = 2
    const val CHAT_SELF = 3
    const val CHAT_OTHERS = 4
    const val CHAT_NOTIFY = 5

    // SORT
    const val SORT_RECENT = 11
    const val SORT_LONG = 12
    const val SORT_LIKE = 13

    // Y?N
    const val YES = 20
    const val NO = 21


    const val OK = 200

    // HTTP STATUS CODE
    const val USERS_NOT_FOUND = 400 //가입하지 않은 이메일을 입력한 경우
    const val REQUEST_ERROR = 400 // 올바른 형식이 아닌 경우
    const val USERS_INVALID_ACCESS = 401 // 인가 접근 경로가 잘못된 경우
    const val USERS_NOT_AUTHORIZED = 401 // 인가 요청에 실패한 경우
    const val UNAUTHORIZED = 401 // 헤더에 토큰이 없는경우, JWT토큰이 만료되었거나 유효하지 않은 경우
    const val FAILED_TO_LOGIN = 404 // 로그인에 실패한 경우
    const val BANNED_USER_IN_LOGIN = 404 // 비활성화된 유저가 접근한 경우
    const val FAILED_TO_JWT = 500 // 토큰 발급에 실패한 경우
    const val POST_FAIL_USER = 500
    const val DATABASE = 500 // 서버내부 오류로 회원 정보 조회에 실패 한 경우
    const val UPDATE_FAIL_PROFILE = 500 //서버 내부 오류로 프로필 수정에 실패한 경우
    const val FAILED_JWT_IN_ACCESS = 500 // 토근을 헤더에 넣지 못한 경우


    // STRING CODE
    const val DEFAULT = "default"
    const val BORDER_TITLE = "title"
    const val BORDER_MAIN_MENU = "borderMainMenu"
    const val BORDER_IMAGE_URL ="borderImageUrl"
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
    const val MASTER_ID = "masterId"
    const val IMAGE_URI ="imageUri"
    const val IMAGE_PATH="imagePath"

    // VALIDATION
    const val EMAIL_VALIDATION =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val NICKNAME_VALIDATION = "^[가-힣]{1,8}$"
    const val COMMENT_VALIDATION = "^.{1,24}$"
    const val PW_VALIDATION = """^[0-9a-zA-Z!@#$%^+\-=]*$"""

    // retrofit2 isSuccessful: false , message
    const val SUCCESS_STRING_MODIFY = "프로필 수정이 완료되었습니다."
    const val ERROR_STRING_INPUT = "입력값을 확인해주세요"
    const val ERROR_STRING_NULL_STORY ="아직 오픈한 이야기 집이 없습니다."
    const val ERROR_STRING_NULL_ALL_STORY = "이야기 집이 없습니다."
    const val ERROR_STRING_DUPLICATE = "중복된 이메일입니다."
    const val ERROR_STRING_FAILED_SIGN_UP = "회원가입에 실패했습니다."
    const val ERROR_STRING_DATABASE = "데이터베이스 연결에 실패했습니다."
    const val ERROR_STRING_FAIL_UPDATE_PROFILE = "프로필 수정에 실패했습니다."

    // REQUEST CODE
    const val REQUEST_CODE_FOR_INTENT = 1002
    const val REQUEST_CODE_PROFILE_IMAGE = 2001
    const val REQUEST_CODE_BORDER_IMAGE = 2002
    const val REQUEST_CODE_PERMISSIONS = 2000
}
