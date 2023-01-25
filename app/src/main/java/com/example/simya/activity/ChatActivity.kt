package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.R
import com.example.simya.adpter.chatAdapter.ChatRVAdapter
import com.example.simya.databinding.ActivityDrawerChatBinding
import com.example.simya.testData.TestChatData
import com.example.simya.testData.TestUserData

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityDrawerChatBinding
    private lateinit var dataList: ArrayList<TestChatData>
    lateinit var sendUser: TestUserData
    lateinit var receiveUser: TestUserData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.includedChat.includedDefault.tvDefaultChatTitle.text = "테스트용 이야기방"
        testUserInit()
        //표시할 채팅 리스트
        dataList = arrayListOf()
        dataList.apply {
            add(TestChatData(sendUser,"오늘은 레이아웃 끝내는날","오후 2시 22분"))
            add(TestChatData(receiveUser,"내용이 굉장히 길면 어디까지 될까에 대한 궁금증 해결을 위한 예시용 텍스트, 최대 20까지 잡았으나 적용이 잘되나 확인이 필요하다","오후 2시 24분"))
            add(TestChatData(sendUser,"앞으로 추가해야할 내용은","오후 2시 22분"))
            add(TestChatData(receiveUser,"리사이클러뷰에 싱글 셀렉션을 추가해서 각 아이템을 클릭했을때 효과를 부여해야함 간판 수정에서는 구현했지만 앞으로 멀티프로필이나 현재 구현한 내용도 확실한 구현이 아닌 임시방편용 느낌이 강하다 디자이너와 이야기를 해야한다.","오후 2시 22분"))
            add(TestChatData(sendUser,"끝","오후 2시 22분"))
            add(TestChatData(sendUser,"중복 처리를 위한 확인용 텍스트","오후 2시 22분"))
        }
        val dataRVAdapter = ChatRVAdapter(this,dataList)
        binding.includedChat.rvChatList.adapter = dataRVAdapter
        binding.includedChat.rvChatList.layoutManager = LinearLayoutManager(this).apply{
            stackFromEnd = true
        }
    }
    // 데이터 가공 해야함
    //
    private fun testUserInit(){
        sendUser = TestUserData("초이", R.drawable.test_choi,1)
        receiveUser = TestUserData("푸",R.drawable.test_poo,2)

    }
}