package com.example.simya.src.main.chat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.util.Constants
import com.example.simya.util.Constants.CHAT_NOTIFY
import com.example.simya.util.Constants.CHAT_OTHERS
import com.example.simya.util.Constants.CHAT_SELF
import com.example.simya.util.Constants.HOUSE_ID
import com.example.simya.util.Constants.PROFILE_ID
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.src.main.story.StoryIntroActivity
import com.example.simya.util.data.ChatRVData
import com.example.simya.util.data.UserData
import com.example.simya.databinding.ActivityDrawerChatBinding
import com.example.simya.databinding.SnackbarLayoutBinding
import com.example.simya.src.main.chat.adapter.ChatDrawerRVAdapter
import com.example.simya.src.main.chat.adapter.ChatRVAdapter
import com.example.simya.src.testData.TestChatDrawerProfileData
import com.example.simya.src.testData.TestUserData
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import com.ms.square.android.expandabletextview.ExpandableTextView
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Header
import org.json.JSONObject


class ChatActivity : BaseActivity<ActivityDrawerChatBinding>(ActivityDrawerChatBinding::inflate)
{
    private lateinit var dataList: ArrayList<ChatRVData>
    private lateinit var profileList: ArrayList<TestChatDrawerProfileData>
    lateinit var sendUser: TestUserData
    lateinit var receiveUser1: TestUserData
    lateinit var receiveUser2: TestUserData
    lateinit var receiveUser3: TestUserData
    lateinit var receiveUser4: TestUserData

    //Stomp Test
    private val url = "ws://10.0.2.2:8080/ws-stomp"
    private lateinit var stompConnection: Disposable
    private lateinit var topic: Disposable
    private val intervalMillis = 1000L
    private val jsonObject = JSONObject()
    private lateinit var chatObject: JSONObject

    //    private val client = OkHttpClient()
    private val client = OkHttpClient.Builder()
        .addInterceptor {
            it.proceed(
                it.request().newBuilder().header(
                    "Access-Token",
                    UserData.accessToken
                )
                    .header(
                        "Refresh-Token",
                        UserData.refreshToken
                    ).build()
            )
        }
        .build()
    private val stomp = StompClient(client, intervalMillis)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 인텐트 데이터값이 0 일경우 채팅방 예외처리 -> 다시메인으로
        testUserCheck(Constants.CHAT_GUEST_CODE)
        onNotify(binding.root, "안녕")
        init()
    }

    private fun init() {
        Log.d("Status", "CHAT ACTIVITY")
        stomp.url = "ws://10.0.2.2:8080/simya/ws-stomp/websocket"
        stompConnection = stomp.connect().subscribe {
            when (it.type) {
                Event.Type.OPENED -> {
                    Log.d("CONNECT", "OPENED")
                    enterNotify()
                    topic = stomp.join("/sub/simya/chat/room/${intent.getLongExtra(HOUSE_ID, 0)}")
                        .subscribe { chatData ->
                            Header(
                                "Access-Token",
                                UserData.accessToken
                            )
                            Header(
                                "Refresh-Token",
                                UserData.refreshToken
                            )
                            chatObject = JSONObject(chatData)
                            receiveMessage(chatObject)

                        }
                }
                Event.Type.CLOSED -> {
                    Log.d("CONNECT", "CLOSED")
                }
                Event.Type.ERROR -> {
                    Log.d("CONNECT", "ERROR")
                    Log.d("Client", client.toString())
                }

                else -> {}
            }
        }

        val drawerLayout = binding.dlChat
        binding.includedChat.includedDefault.tvDefaultChatTitle.text = "테스트용 이야기방"
        binding.includedChat.includedDefault.ibDefaultChatDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        // 테스트 유저 초기화
        testUserInit()

        //표시할 채팅 리스트
        dataList = arrayListOf()
        val dataRVAdapter = ChatRVAdapter(this, dataList)
        binding.includedChat.rvChatList.adapter = dataRVAdapter
        binding.includedChat.rvChatList.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
            moveLastItem(this)
        }

        // test용 메시지 보내기
        binding.includedChat.ibChatSend.setOnClickListener {
            if (binding.includedChat.etChatInput.text.isNotEmpty()) {
                Log.d("send message", binding.includedChat.etChatInput.text.toString())

                sendMessage()
                binding.includedChat.etChatInput.text = null

            }

            testDrawerUserListed()
        }

    }
    private fun sendMessage(){
        jsonObject.put("type", "TALK")
        jsonObject.put("roomId", intent.getLongExtra(HOUSE_ID, 0))
        jsonObject.put("sender", UserData.getProfileName())
        jsonObject.put("token", convertToken(UserData.accessToken))
        jsonObject.put("message", binding.includedChat.etChatInput.text.toString())
        jsonObject.put("userCount", 1)
        Log.d("JSON OBJECT MESSAGE", jsonObject.get("message").toString())
        Log.d("Check JSON OBJECT", jsonObject.toString())
        stomp.send(
            "/pub/simya/chat/androidMessage",
            jsonObject.toString()
        ).subscribe {
            if (it) {
            }
        }
    }
    private fun receiveMessage(chat: JSONObject) {
        Log.d("Receive Message is", chat.toString())
        val profileId = chat.getLong("profileId")
        val picture = chat.getString("picture")
        val sender = chat.getString("sender")
        val message = chat.getString("message")
        val type = chat.getString("type")
        runOnUiThread {
            var senderType = 0
            Log.d("profileId",profileId.toString())
            Log.d("intent profileId",intent.getLongExtra(PROFILE_ID,0).toString())
            when(type){
                "ENTER"-> {
                    senderType = CHAT_NOTIFY
                }
                "TALK" ->{
                    senderType = if (profileId == intent.getLongExtra(PROFILE_ID, 0)) {
                        CHAT_SELF
                    } else {
                        CHAT_OTHERS
                    }
                }
            }

            dataList.add(ChatRVData(profileId, picture, sender, message, senderType))
            Log.d("sender is",sender)
            Log.d("sender type",senderType.toString())
            binding.includedChat.rvChatList.apply {
                adapter!!.notifyItemInserted(dataList.size - 1)
                scrollToPosition(dataList.size - 1)
            }
        }
    }

    private fun enterNotify(){
        jsonObject.put("type", "ENTER")
        jsonObject.put("roomId", intent.getLongExtra(HOUSE_ID, 0))
        jsonObject.put("sender", UserData.getProfileName())
        jsonObject.put("token", convertToken(UserData.accessToken))
        jsonObject.put("message", "입장")
        jsonObject.put("userCount", 1)
        stomp.send(
            "/pub/simya/chat/androidMessage",
            jsonObject.toString()
        ).subscribe {
            if (it) {
            }
        }
    }
    private fun convertToken(testAccessToken: String): String {
        return testAccessToken.substring(7)
    }

    private fun moveLastItem(layoutManager: LinearLayoutManager) {
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable { layoutManager.scrollToPositionWithOffset(dataList.size - 1, 0) }, 300
        )
    }


    private fun testUserCheck(code: Int) {
        if (code == Constants.CHAT_MASTER_CODE) {
            // 주인장 캐스팅
        } else if (
            code == Constants.CHAT_GUEST_CODE) {
            setGuestType()
        }
    }

    private fun setGuestType() {
        binding.btnChatPause.isInvisible = true
        binding.ibChatCloseOrLike.setImageResource(R.drawable.ic_heart_off)
    }

    private fun testDrawerUserListed() {
        Log.d("테스트 드로어 유저 리스트","true")
        binding.btnDrawerIntro.setOnClickListener {
            val intent = Intent(this, StoryIntroActivity::class.java)
            startActivity(intent)
        }
        //오늘의 메뉴로 이동
        binding.btnDrawerToday
        profileList = arrayListOf()
        profileList.apply {
            add(TestChatDrawerProfileData(sendUser.nick, sendUser.image))
            add(TestChatDrawerProfileData(receiveUser1.nick, receiveUser1.image))
            add(TestChatDrawerProfileData(receiveUser2.nick, receiveUser2.image))
            add(TestChatDrawerProfileData(receiveUser3.nick, receiveUser3.image))
            add(TestChatDrawerProfileData(receiveUser4.nick, receiveUser4.image))
        }
        val testDataRVAdapter = ChatDrawerRVAdapter(this, profileList)
        binding.rvChatProfileList.adapter = testDataRVAdapter
        binding.rvChatProfileList.layoutManager = LinearLayoutManager(this)
    }

    // 테스트 유저용 초기화
    private fun testUserInit() {
        sendUser = TestUserData("초이", R.drawable.test_choi, Constants.CHAT_MASTER_CODE)
        receiveUser1 = TestUserData("푸", R.drawable.test_poo, Constants.CHAT_GUEST_CODE)
        receiveUser2 = TestUserData("왁", R.drawable.test_wak, Constants.CHAT_GUEST_CODE)
        receiveUser3 = TestUserData("쭈니", R.drawable.test_jooni, Constants.CHAT_GUEST_CODE)
        receiveUser4 = TestUserData("채니", R.drawable.test_chani, Constants.CHAT_GUEST_CODE)
    }

    //키보드 내려감
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

    // 공지사항 버튼 애니이션

    private fun onNotify(view: View, message: String) {
        binding.includedChat.ibTodayMenu.setOnClickListener {
            var snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)

            val snackBarView: View = layoutInflater.inflate(R.layout.snackbar_layout, null)
            val snackBarBinding = SnackbarLayoutBinding.bind(snackBarView)
            snackBar.view.setBackgroundColor(Color.TRANSPARENT)
            snackBarBinding.snackBarMessage.text = message

            val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
            snackBarLayout.addView(snackBarView)

            snackBar.show()
            if (binding.includedChat.cvTodayMenu2.visibility == View.VISIBLE) {
                binding.includedChat.cvTodayMenu2.visibility = View.GONE
                binding.includedChat.ibTodayMenu.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.includedChat.cvTodayMenu2.visibility = View.VISIBLE
                binding.includedChat.ibTodayMenu.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }
    }

}