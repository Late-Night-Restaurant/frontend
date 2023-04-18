//package com.example.simya.src.ui.view.chat
//
//import android.content.Context
//import android.graphics.Color
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.util.Log
//import android.view.MotionEvent
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import androidx.core.view.GravityCompat
//import androidx.core.view.isInvisible
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.simya.util.Constants.CHAT_NOTIFY
//import com.example.simya.util.Constants.CHAT_OTHERS
//import com.example.simya.util.Constants.CHAT_SELF
//import com.example.simya.util.Constants.HOUSE_ID
//import com.example.simya.util.Constants.PROFILE_ID
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.config.BaseResponse
//import com.example.simya.util.data.ChatRVData
//import com.example.simya.util.data.UserData
//import com.example.simya.databinding.ActivityDrawerChatBinding
//import com.example.simya.databinding.SnackbarLayoutBinding
//import com.example.simya.src.ui.adapter.chat.ChatDrawerRVAdapter
//import com.example.simya.src.ui.adapter.chat.ChatRVAdapter
//import com.example.simya.src.main.chat.model.ChatDrawerInterface
//import com.example.simya.src.model.ChatProfileListResponse
//import com.example.simya.src.model.UserDTO
//import com.example.simya.util.Constants.BORDER_TITLE
//import com.example.simya.util.Constants.MASTER_ID
//import com.example.simya.util.Constants.SOCKET_URL
//import com.example.simya.util.SampleSnackBar
//import com.example.simya.util.onThrottleClick
//import com.gmail.bishoybasily.stomp.lib.Event
//import com.gmail.bishoybasily.stomp.lib.StompClient
//import com.google.android.material.snackbar.Snackbar
//import io.reactivex.disposables.Disposable
//import okhttp3.OkHttpClient
//import okhttp3.internal.http2.Header
//import org.json.JSONObject
//
//
//class ChatActivity : BaseActivity<ActivityDrawerChatBinding>(ActivityDrawerChatBinding::inflate), ChatDrawerInterface {
//    private lateinit var dataList: ArrayList<ChatRVData>
//    private lateinit var profileList: ArrayList<UserDTO>
//    private lateinit var dataRVAdapter: ChatRVAdapter
//    private lateinit var listDataRVAdapter: ChatDrawerRVAdapter
//    private lateinit var stompConnection: Disposable
//    private lateinit var topic: Disposable
//    private val intervalMillis = 1000L
//    private val jsonObject = JSONObject()
//    private lateinit var chatObject: JSONObject
//    private var thisHouseId: Long = 0
//    private var thisHouseMasterId: Long = 0
//    private var allChatStatus = true
//    private lateinit var thisHouseName: String
//
//    private val client = OkHttpClient.Builder()
//        .addInterceptor {
//            it.proceed(
//                it.request().newBuilder().header(
//                    "Access-Token",
//                    UserData.accessToken
//                )
//                    .header(
//                        "Refresh-Token",
//                        UserData.refreshToken
//                    ).build()
//            )
//        }
//        .build()
//    private val stomp = StompClient(client, intervalMillis)
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        thisHouseId = intent.getLongExtra(HOUSE_ID, 0)
//        thisHouseMasterId = intent.getLongExtra(MASTER_ID,0)
//        thisHouseName = intent.getStringExtra(BORDER_TITLE)!!
//        checkHouseAndMasterId(thisHouseId,thisHouseMasterId)
//        checkUserTypeSwapUI(UserData.getProfileId())
//        init()
//    }
//    private fun checkHouseAndMasterId(houseId: Long,masterId: Long){
//        Log.d("checkHouseId",houseId.toString())
//        Log.d("checkMasterId",masterId.toString())
//        if(houseId == 0L || masterId == 0L){
//            Log.d("ERROR","존재하지 않는 방입니다.")
//            finish()
//        }
//    }
//    private fun init() {
//        binding.tvDrawerChat.text = thisHouseName
//        Log.d("Status", "CHAT ACTIVITY")
//        stomp.url = SOCKET_URL
//        stompConnection = stomp.connect().subscribe {
//            when (it.type) {
//                Event.Type.OPENED -> {
//                    Log.d("CONNECT", "OPENED")
//                    enterNotify()
//                    topic = stomp.join("/sub/simya/chat/room/${thisHouseId}")
//                        .subscribe { chatData ->
//                            Header(
//                                "Access-Token",
//                                UserData.accessToken
//                            )
//                            Header(
//                                "Refresh-Token",
//                                UserData.refreshToken
//                            )
//                            chatObject = JSONObject(chatData)
//                            Log.d("chatObject check",chatObject.toString())
//                            receiveMessage(chatObject)
//
//                        }
//                }
//                Event.Type.CLOSED -> {
//                    Log.d("CONNECT", "CLOSED")
//                }
//                Event.Type.ERROR -> {
//                    Log.d("CONNECT", "ERROR")
//                    Log.d("Client", client.toString())
//                }
//
//                else -> {}
//            }
//        }
//
//        val drawerLayout = binding.dlChat
//        binding.includedChat.includedDefault.tvDefaultChatTitle.text = "테스트용 이야기방"
//        binding.includedChat.includedDefault.ibDefaultChatDrawer.onThrottleClick {
//            //채팅 내리기
//            val imm: InputMethodManager =
//                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
//            drawerLayout.openDrawer(GravityCompat.START)
//        }
//        //표시할 채팅 리스트
//        dataList = arrayListOf()
//        dataRVAdapter = ChatRVAdapter(this, dataList)
//        binding.includedChat.rvChatList.adapter = dataRVAdapter
//        binding.includedChat.rvChatList.layoutManager = LinearLayoutManager(this).apply {
//            stackFromEnd = true
//            moveLastItem(this)
//        }
//
//        binding.includedChat.ibChatSend.onThrottleClick {
//            if (binding.includedChat.etChatInput.text.isNotEmpty()) {
//                Log.d("send message", binding.includedChat.etChatInput.text.toString())
//                sendTypeMessage("TALK", binding.includedChat.etChatInput.text.toString())
//                binding.includedChat.etChatInput.text = null
//
//            }
//        }
//        binding.btnChatPause.onThrottleClick {
//            if(allChatStatus){
//                sendTypeMessage("FREEZE","채팅방 얼리기")
//                sendTypeMessage("NOTIFY","채팅방 얼리기")
//                binding.btnChatPause.text = "채팅방 녹이기"
//                allChatStatus = !allChatStatus
//            }else{
//                sendTypeMessage("RELEASE-FREEZE","채팅방 녹이기")
//                sendTypeMessage("NOTIFY","채팅방 녹이기")
//                binding.btnChatPause.text ="채팅방 얼리기"
//                allChatStatus = !allChatStatus
//            }
//        }
//    }
////    private fun getGuestProfileList(){
////        ChatDrawerService(this).tryGetChatProfileList(thisHouseId)
////    }
////    private fun reloadGetGuestProfileList(){
////        listDataRVAdapter.notifyItemRangeRemoved(0,profileList.size-1)
////        getGuestProfileList()
////    }
//    // 공통
//    private fun sendTypeMessage(type: String, message: String) {
//        jsonObject.put("type", type)
//        jsonObject.put("roomId", intent.getLongExtra(HOUSE_ID, 0))
//        jsonObject.put("sender", UserData.getProfileName())
//        jsonObject.put("token", convertToken(UserData.accessToken))
//        jsonObject.put("message", message)
//        jsonObject.put("userCount", 1)
//
//        Log.d("JSON OBJECT MESSAGE", jsonObject.get("message").toString())
//        Log.d("Check JSON OBJECT", jsonObject.toString())
//        stomp.send(
//            "/pub/simya/chat/androidMessage",
//            jsonObject.toString()
//        ).subscribe()
//    }
//    private fun enterNotify() {
//        sendTypeMessage("ENTER", "입장")
//    }
//    private fun aWayNotify(){
//        sendTypeMessage("QUIT", "입장")
//    }
//    private fun chatBen() {
//        binding.includedChat.ibChatSend.isEnabled = false
//        binding.includedChat.ibChatSend.isClickable = false
//        binding.includedChat.etChatInput.setText("채팅이 금지되었습니다.")
//        binding.includedChat.etChatInput.isEnabled = false
//        sendTypeMessage("NOTIFY", "${UserData.getProfileName()}+님의 채팅이 얼었습니다.")
//    }
//
//    private fun releaseChatBen() {
//        binding.includedChat.ibChatSend.isEnabled = true
//        binding.includedChat.ibChatSend.isClickable = true
//        binding.includedChat.etChatInput.setText("")
//        binding.includedChat.etChatInput.isEnabled = true
//        sendTypeMessage("NOTIFY", "${UserData.getProfileName()}+님의 채팅이 녹았습니다!")
//    }
//
//    private fun chatFreeze() {
//        binding.includedChat.ibChatSend.isEnabled = false
//        binding.includedChat.ibChatSend.isClickable = false
//        binding.includedChat.etChatInput.setText("채팅이 금지되었습니다.")
//        binding.includedChat.etChatInput.isEnabled = false
//    }
//
//    private fun releaseChatFreeze() {
//        binding.includedChat.ibChatSend.isEnabled = true
//        binding.includedChat.ibChatSend.isClickable = true
//        binding.includedChat.etChatInput.setText("")
//        binding.includedChat.etChatInput.isEnabled = true
//
//    }
//    private fun chatForce(){
//        stompConnection.dispose()
//        finish()
//    }
//
//    private fun receiveMessage(chat: JSONObject) {
//        Log.d("Receive Message is", chat.toString())
//        val profileId = chat.getLong("profileId")
//        val picture = chat.getString("pictureUrl")
//        val sender = chat.getString("sender")
//        val message = chat.getString("message")
//        val type = chat.getString("type")
//        var senderType = 0
//        Log.d("profileId", profileId.toString())
//        Log.d("intent profileId", intent.getLongExtra(PROFILE_ID, 0).toString())
//        when (type) {
//            "ENTER" -> {
//                senderType = CHAT_NOTIFY
//                addMessage(ChatRVData(profileId, picture, sender, message, senderType))
//            }
//            "NOTIFY" -> {
//                senderType = CHAT_NOTIFY
//                addMessage(ChatRVData(profileId, picture, sender, message, senderType))
//            }
//            "TALK" -> {
//                senderType = if (profileId == UserData.getProfileId()) {
//                    CHAT_SELF
//                } else {
//                    CHAT_OTHERS
//                }
//                addMessage(ChatRVData(profileId, picture, sender, message, senderType))
//            }
//            "BEN" -> {
//                senderType = CHAT_NOTIFY
//                if (message.toLong() == UserData.getProfileId()) {
//                    chatBen()
//                }
//            }
//            "FREEZE" -> {
//                senderType = CHAT_NOTIFY
//                if(UserData.getProfileId()!= thisHouseMasterId){
//                    chatFreeze()
//                }
//            }
//            "RELEASE-BEN" -> {
//                senderType = CHAT_NOTIFY
//                if (message.toLong() == UserData.getProfileId()) {
//                    releaseChatBen()
//                }
//            }
//            "RELEASE-FREEZE" -> {
//                senderType = CHAT_NOTIFY
//                if(UserData.getProfileId()!= thisHouseMasterId){
//                    releaseChatFreeze()
//                }
//                // 메시지는 얼리기버튼 해제와 동시에 보냄
//            }
//            "FORCE" -> {
//                senderType = CHAT_NOTIFY
//                if (message.toLong() == UserData.getProfileId()) {
//                    chatForce()
//                }
//            }
//            "AWAY" -> {
//                senderType = CHAT_NOTIFY
//                // 주인장은 자리비움, 손님은 나가기
//            }
//            "CLOSED" -> {
//                senderType = CHAT_NOTIFY
//                // 폐점
//            }
//            else -> {
//                Log.d("Stomp Type ERROR", "처리할 수 없는 요청입니다.")
//            }
//
//        }
//
//        Log.d("sender is", sender)
//        Log.d("sender type", senderType.toString())
//    }
//
//    private fun addMessage(chat: ChatRVData) {
//        runOnUiThread {
//            dataList.add(chat)
//            binding.includedChat.rvChatList.apply {
//                dataRVAdapter.notifyItemInserted(dataList.size - 1)
//                scrollToPosition(dataList.size - 1)
//            }
//        }
//    }
//
//
//    private fun convertToken(testAccessToken: String): String {
//        return testAccessToken.substring(7)
//    }
//
//    private fun moveLastItem(layoutManager: LinearLayoutManager) {
//        Handler(Looper.getMainLooper()).postDelayed(
//            Runnable { layoutManager.scrollToPositionWithOffset(dataList.size - 1, 0) }, 300
//        )
//    }
//
//
//    private fun checkUserTypeSwapUI(profileId: Long) {
//        if (profileId == 0L){
//            Log.d("ERROR","잘못된 요청입니다.")
//            finish()
//        } else if (profileId != thisHouseMasterId) {
//            setGuestType()
//        }
//    }
//
//    private fun setGuestType() {
//        binding.btnChatPause.isInvisible = true
//        binding.ibChatCloseOrLike.setImageResource(R.drawable.ic_heart_off)
//    }
//
////    private fun testDrawerUserListed() {
////        Log.d("테스트 드로어 유저 리스트", "true")
////        binding.btnDrawerIntro.setOnClickListener {
////            val intent = Intent(this, StoryIntroActivity::class.java)
////            startActivity(intent)
////        }
////        profileList = arrayListOf()
////        profileList.apply {
////            add(TestChatDrawerProfileData(sendUser.nick, sendUser.image))
////            add(TestChatDrawerProfileData(receiveUser1.nick, receiveUser1.image))
////            add(TestChatDrawerProfileData(receiveUser2.nick, receiveUser2.image))
////            add(TestChatDrawerProfileData(receiveUser3.nick, receiveUser3.image))
////            add(TestChatDrawerProfileData(receiveUser4.nick, receiveUser4.image))
////        }
////        val testDataRVAdapter = ChatDrawerRVAdapter(this, profileList)
////        binding.rvChatProfileList.adapter = testDataRVAdapter
////        binding.rvChatProfileList.layoutManager = LinearLayoutManager(this)
////    }
//
//    // 테스트 유저용 초기화
////    private fun testUserInit() {
////        sendUser = TestUserData("초이", R.drawable.test_choi, Constants.CHAT_MASTER_CODE)
////        receiveUser1 = TestUserData("푸", R.drawable.test_poo, Constants.CHAT_GUEST_CODE)
////        receiveUser2 = TestUserData("왁", R.drawable.test_wak, Constants.CHAT_GUEST_CODE)
////        receiveUser3 = TestUserData("쭈니", R.drawable.test_jooni, Constants.CHAT_GUEST_CODE)
////        receiveUser4 = TestUserData("채니", R.drawable.test_chani, Constants.CHAT_GUEST_CODE)
////    }
//
//    //키보드 내려감
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        val imm: InputMethodManager =
//            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
//        return true
//    }
//
//    // 공지사항 버튼 애니이션
//    private fun onNotify(view: View, message: String) {
//        binding.includedChat.ibTodayMenuButton.onThrottleClick {
//            var snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
//
//            val snackBarView: View = layoutInflater.inflate(R.layout.snackbar_layout, null)
//            val snackBarBinding = SnackbarLayoutBinding.bind(snackBarView)
//            snackBar.view.setBackgroundColor(Color.TRANSPARENT)
//            snackBarBinding.snackBarMessage.text = message
//
//            val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
//            snackBarLayout.addView(snackBarView)
//
//            snackBar.show()
//            if (binding.includedChat.cvTodayMenuOn.visibility == View.VISIBLE) {
//                binding.includedChat.cvTodayMenuOn.visibility = View.GONE
//                binding.includedChat.ibTodayMenuButton.animate().apply {
//                    duration = 300
//                    rotation(0f)
//                }
//            } else {
//                binding.includedChat.cvTodayMenuOn.visibility = View.VISIBLE
//                binding.includedChat.ibTodayMenuButton.animate().apply {
//                    duration = 300
//                    rotation(180f)
//                }
//            }
//        }
//    }
//    private fun clickProfile(){
//        dataRVAdapter.setOnItemClickListener(object: ChatRVAdapter.OnItemClickListener{
//            override fun onItemClick(data: ChatRVData, position: Int) {
//                if(UserData.getProfileId() == thisHouseMasterId){
//                    // 다보여주기
//                }
//                else{
//
//                }
//            }
//        })
//    }
//
//    override fun onGetChatProfileListSuccess(response: ChatProfileListResponse) {
//        Log.d("채팅방 유저 정보 조회", "true")
//        profileList = arrayListOf()
//        for(i: Int in 0 until response.result.size){
//            profileList.add(response.result[i])
//        }
//        listDataRVAdapter = ChatDrawerRVAdapter(this, profileList)
//        binding.rvChatProfileList.adapter = listDataRVAdapter
//        binding.rvChatProfileList.layoutManager = LinearLayoutManager(this)
//    }
//
//    override fun onGetChatProfileListFailure(response: BaseResponse) {
//        SampleSnackBar.make(binding.root,"채팅방 유저 정보를 조회하는데 실패 했습니다.")
//        dismissLoadingDialog()
//    }
//
//    override fun onGetChatProfileListDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//
//}