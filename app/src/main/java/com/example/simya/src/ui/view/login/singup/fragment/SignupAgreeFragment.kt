package com.example.simya.src.ui.view.login.singup.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signIn.EmailLoginActivity
import com.example.simya.src.ui.view.login.singup.SignupActivity
import com.example.simya.databinding.FragmentSignupAgreeBinding
import com.example.simya.util.dialog.AgreeDialogInterface


class SignupAgreeFragment: BaseFragment<FragmentSignupAgreeBinding>(R.layout.fragment_signup_agree),
    SignupActivity.onBackPressedListener, AgreeDialogInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agreeCheck()
//        showAgreeDetail()
        binding.btnSignupNextAgree.setOnClickListener {
        }

    }


    private fun agreeCheck() : Boolean {
        val agreeAll = binding.cbSignupAgreeAll
        val agreeService = binding.cbSignupAgreeService
        val agreeInfo = binding.cbSignupAgreeInfo

        if (agreeAll.isChecked) {
            agreeService.isChecked = true
            agreeInfo.isChecked = true
            TrueButton()
        } else {
            agreeService.isChecked = false
            agreeInfo.isChecked = false
            FalseButton()
        }

        agreeAll.setOnClickListener {
            if (agreeAll.isChecked) {
                agreeService.isChecked = true
                agreeInfo.isChecked = true
                TrueButton()
            } else {
                agreeService.isChecked = false
                agreeInfo.isChecked = false
                FalseButton()
            }
        }

        agreeInfo.setOnClickListener {
            if (agreeInfo.isChecked) {
                if (agreeService.isChecked) {
                    agreeAll.isChecked = true
                    TrueButton()
                } else {
                    agreeAll.isChecked = false
                    FalseButton()
                }
            }
            if (!agreeInfo.isChecked) {
                agreeAll.isChecked = false
                FalseButton()
            }
        }

        agreeService.setOnClickListener {
            if (agreeService.isChecked) {
                if (agreeInfo.isChecked) {
                    agreeAll.isChecked = true
                    TrueButton()
                } else {
                    agreeAll.isChecked = false
                    FalseButton()
                }
            }
            if (!agreeService.isChecked) {
                agreeAll.isChecked = false
                FalseButton()
            }
        }



        return agreeAll.isChecked

    }

    private fun TrueButton() {
        binding.btnSignupNextAgree.isEnabled = true
        binding.btnSignupNextAgree.isClickable = true
        binding.btnSignupNextAgree.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupNextAgree.setTextColor(resources.getColor(R.color.Gray_03))

    }

    private fun FalseButton() {
        binding.btnSignupNextAgree.isEnabled = false
        binding.btnSignupNextAgree.isClickable = false
        binding.btnSignupNextAgree.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupNextAgree.setTextColor(resources.getColor(R.color.Gray_10))

    }

    private fun initAgree() {
        val agreeAll = binding.cbSignupAgreeAll
        val agreeService = binding.cbSignupAgreeService
        val agreeInfo = binding.cbSignupAgreeInfo

        agreeAll.isChecked = false
        agreeService.isChecked = false
        agreeInfo.isChecked = false
    }

    override fun onBackPressed() {
        val intent = Intent(this.context, EmailLoginActivity::class.java)
        startActivity(intent)
    }

//    private fun showAgreeDetail() {
//        binding.btnSignupService.setOnClickListener {
//            AgreeDialog("< 심야식당 >('https://www.youtube.com/watch?v=4ocGd4-5zbc&t=2008'이하 '심야식당')은(는) 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.\n" +
//            "\n" +
//                    "○ 이 개인정보처리방침은 2023년 1월 1부터 적용됩니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "제1조(개인정보의 처리 목적)\n" +
//                    "\n" +
//                    "< 심야식당 >('https://www.youtube.com/watch?v=4ocGd4-5zbc&t=2008'이하 '심야식당')은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.\n" +
//                    "\n" +
//                    "1. 홈페이지 회원가입 및 관리\n" +
//                    "\n" +
//                    "회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리, 서비스 부정이용 방지, 만14세 미만 아동의 개인정보 처리 시 법정대리인의 동의여부 확인 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "2. 민원사무 처리\n" +
//                    "\n" +
//                    "민원인의 신원 확인, 민원사항 확인 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "3. 재화 또는 서비스 제공\n" +
//                    "\n" +
//                    "서비스 제공, 콘텐츠 제공, 맞춤서비스 제공, 본인인증을 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "4. 마케팅 및 광고에의 활용\n" +
//                    "\n" +
//                    "신규 서비스(제품) 개발 및 맞춤 서비스 제공, 이벤트 및 광고성 정보 제공 및 참여기회 제공 등을 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제2조(개인정보의 처리 및 보유 기간)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.\n" +
//                    "\n" +
//                    "② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.\n" +
//                    "\n" +
//                    "1.<민원사무 처리>\n" +
//                    "<민원사무 처리>와 관련한 개인정보는 수집.이용에 관한 동의일로부터<1년>까지 위 이용목적을 위하여 보유.이용됩니다.\n" +
//                    "보유근거 : 개인\n" +
//                    "관련법령 : 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년\n" +
//                    "예외사유 : 탈퇴\n" +
//                    "\n" +
//                    "\n" +
//                    "제3조(처리하는 개인정보의 항목)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 다음의 개인정보 항목을 처리하고 있습니다.\n" +
//                    "\n" +
//                    "1< 홈페이지 회원가입 및 관리 >\n" +
//                    "필수항목 : 이메일, 비밀번호, 이름\n" +
//                    "선택항목 :\n" +
//                    "\n" +
//                    "\n" +
//                    "제4조(개인정보의 제3자 제공에 관한 사항)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 개인정보를 제1조(개인정보의 처리 목적)에서 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정 등 「개인정보 보호법」 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.\n" +
//                    "\n" +
//                    "② < 심야식당 >은(는) 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.\n" +
//                    "\n" +
//                    "1. < 심야식당 >\n" +
//                    "개인정보를 제공받는 자 : 심야식당\n" +
//                    "제공받는 자의 개인정보 이용목적 : 이메일, 비밀번호, 이름\n" +
//                    "제공받는 자의 보유.이용기간: 1년\n" +
//                    "\n" +
//                    "\n" +
//                    "제5조(개인정보처리의 위탁에 관한 사항)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.\n" +
//                    "\n" +
//                    "1. < >\n" +
//                    "위탁받는 자 (수탁자) :\n" +
//                    "위탁하는 업무의 내용 :\n" +
//                    "위탁기간 :\n" +
//                    "② < 심야식당 >은(는) 위탁계약 체결시 「개인정보 보호법」 제26조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.\n" +
//                    "\n" +
//                    "③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제6조(개인정보의 파기절차 및 파기방법)\n" +
//                    "\n" +
//                    "\n" +
//                    "① < 심야식당 > 은(는) 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.\n" +
//                    "\n" +
//                    "② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보를 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.\n" +
//                    "1. 법령 근거 :\n" +
//                    "2. 보존하는 개인정보 항목 : 계좌정보, 거래날짜\n" +
//                    "\n" +
//                    "③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.\n" +
//                    "1. 파기절차\n" +
//                    "< 심야식당 > 은(는) 파기 사유가 발생한 개인정보를 선정하고, < 심야식당 > 의 개인정보 보호책임자의 승인을 받아 개인정보를 파기합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제7조(정보주체와 법정대리인의 권리·의무 및 그 행사방법에 관한 사항)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "① 정보주체는 심야식당에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.\n" +
//                    "\n" +
//                    "② 제1항에 따른 권리 행사는심야식당에 대해 「개인정보 보호법」 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 심야식당은(는) 이에 대해 지체 없이 조치하겠습니다.\n" +
//                    "\n" +
//                    "③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다.이 경우 “개인정보 처리 방법에 관한 고시(제2020-7호)” 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.\n" +
//                    "\n" +
//                    "④ 개인정보 열람 및 처리정지 요구는 「개인정보 보호법」 제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.\n" +
//                    "\n" +
//                    "⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.\n" +
//                    "\n" +
//                    "⑥ 심야식당은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제8조(개인정보의 안전성 확보조치에 관한 사항)\n" +
//                    "\n" +
//                    "< 심야식당 >은(는) 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.\n" +
//                    "\n" +
//                    "1. 정기적인 자체 감사 실시\n" +
//                    "개인정보 취급 관련 안정성 확보를 위해 정기적(분기 1회)으로 자체 감사를 실시하고 있습니다.\n" +
//                    "\n" +
//                    "2. 해킹 등에 대비한 기술적 대책\n" +
//                    "<심야식당>('심야식당')은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.\n" +
//                    "\n" +
//                    "3. 문서보안을 위한 잠금장치 사용\n" +
//                    "개인정보가 포함된 서류, 보조저장매체 등을 잠금장치가 있는 안전한 장소에 보관하고 있습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제9조(개인정보를 자동으로 수집하는 장치의 설치·운영 및 그 거부에 관한 사항)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "① 심야식당 은(는) 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.\n" +
//                    "② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.\n" +
//                    "가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.\n" +
//                    "나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.\n" +
//                    "다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "제10조(행태정보의 수집·이용·제공 및 거부 등에 관한 사항)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "행태정보의 수집·이용·제공 및 거부등에 관한 사항\n" +
//                    "\n" +
//                    "<개인정보처리자명>은(는) 온라인 맞춤형 광고 등을 위한 행태정보를 수집·이용·제공하지 않습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제11조(추가적인 이용·제공 판단기준)\n" +
//                    "\n" +
//                    "< 심야식당 > 은(는) ｢개인정보 보호법｣ 제15조제3항 및 제17조제4항에 따라 ｢개인정보 보호법 시행령｣ 제14조의2에 따른 사항을 고려하여 정보주체의 동의 없이 개인정보를 추가적으로 이용·제공할 수 있습니다.\n" +
//                    "이에 따라 < 심야식당 > 가(이) 정보주체의 동의 없이 추가적인 이용·제공을 하기 위해서 다음과 같은 사항을 고려하였습니다.\n" +
//                    "▶ 개인정보를 추가적으로 이용·제공하려는 목적이 당초 수집 목적과 관련성이 있는지 여부\n" +
//                    "\n" +
//                    "▶ 개인정보를 수집한 정황 또는 처리 관행에 비추어 볼 때 추가적인 이용·제공에 대한 예측 가능성이 있는지 여부\n" +
//                    "\n" +
//                    "▶ 개인정보의 추가적인 이용·제공이 정보주체의 이익을 부당하게 침해하는지 여부\n" +
//                    "\n" +
//                    "▶ 가명처리 또는 암호화 등 안전성 확보에 필요한 조치를 하였는지 여부\n" +
//                    "\n" +
//                    "※ 추가적인 이용·제공 시 고려사항에 대한 판단기준은 사업자/단체 스스로 자율적으로 판단하여 작성·공개함\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제12조(가명정보를 처리하는 경우 가명정보 처리에 관한 사항)\n" +
//                    "\n" +
//                    "< 심야식당 > 은(는) 다음과 같은 목적으로 가명정보를 처리하고 있습니다.\n" +
//                    "\n" +
//                    "▶ 가명정보의 처리 목적\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명정보의 처리 및 보유기간\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명정보의 제3자 제공에 관한 사항(해당되는 경우에만 작성)\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명정보 처리의 위탁에 관한 사항(해당되는 경우에만 작성)\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명처리하는 개인정보의 항목\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 법 제28조의4(가명정보에 대한 안전조치 의무 등)에 따른 가명정보의 안전성 확보조치에 관한 사항\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "제13조 (개인정보 보호책임자에 관한 사항)\n" +
//                    "\n" +
//                    "① 심야식당 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.\n" +
//                    "\n" +
//                    "▶ 개인정보 보호책임자\n" +
//                    "성명 :심야식당\n" +
//                    "직책 :UMC\n" +
//                    "직급 :3기\n" +
//                    "연락처 :010-0000-0000, abc123@naver.com, 000-000-0000\n" +
//                    "※ 개인정보 보호 담당부서로 연결됩니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "▶ 개인정보 보호 담당부서\n" +
//                    "부서명 :\n" +
//                    "담당자 :\n" +
//                    "연락처 :, ,\n" +
//                    "② 정보주체께서는 심야식당 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 심야식당 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제14조(국내대리인의 지정)\n" +
//                    "\n" +
//                    "정보주체는 ｢개인정보 보호법｣ 제39조의11에 따라 지정된 < 심야식당 >의 국내대리인에게 개인정보 관련 고충처리 등의 업무를 위하여 연락을 취할 수 있습니다. < 심야식당 >은(는) 정보주체의 개인정보 관련 고충처리 등 개인정보 보호책임자의 업무 등을 신속하게 처리할 수 있도록 노력하겠습니다.\n" +
//                    "\n" +
//                    "▶ < 심야식당 > 은(는) ｢개인정보 보호법｣ 제39조의11에 따라 국내대리인을 지정하였습니다.\n" +
//                    "\n" +
//                    "- 국내대리인의 성명 : [대리인 성명_직접입력] (법인의 경우 법인명, 대표자의 성명)\n" +
//                    "\n" +
//                    "- 국내대리인의 주소 : [대리인 주소_직접입력] (법인의 경우 영업소 소재지)\n" +
//                    "\n" +
//                    "- 국내대리인의 전화번호 : [대리인 전화번호_직접입력]\n" +
//                    "\n" +
//                    "- 국내대리인의 전자우편 주소 : [대리인 전자우편_직접입력]\n" +
//                    "\n" +
//                    "제15조(개인정보의 열람청구를 접수·처리하는 부서)\n" +
//                    "정보주체는 ｢개인정보 보호법｣ 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다.\n" +
//                    "< 심야식당 >은(는) 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다.\n" +
//                    "\n" +
//                    "▶ 개인정보 열람청구 접수·처리 부서\n" +
//                    "부서명 : 심야식당\n" +
//                    "담당자 : 심야식당\n" +
//                    "연락처 : , ,\n" +
//                    "\n" +
//                    "\n" +
//                    "제16조(정보주체의 권익침해에 대한 구제방법)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회, 한국인터넷진흥원 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기 바랍니다.\n" +
//                    "\n" +
//                    "1. 개인정보분쟁조정위원회 : (국번없이) 1833-6972 (www.kopico.go.kr)\n" +
//                    "2. 개인정보침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)\n" +
//                    "3. 대검찰청 : (국번없이) 1301 (www.spo.go.kr)\n" +
//                    "4. 경찰청 : (국번없이) 182 (ecrm.cyber.go.kr)\n" +
//                    "\n" +
//                    "「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의 정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대 하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.\n" +
//                    "\n" +
//                    "※ 행정심판에 대해 자세한 사항은 중앙행정심판위원회(www.simpan.go.kr) 홈페이지를 참고하시기 바랍니다.\n" +
//                    "\n" +
//                    "제17조(영상정보처리기기 운영·관리에 관한 사항)\n" +
//                    "① < 심야식당 >은(는) 아래와 같이 영상정보처리기기를 설치·운영하고 있습니다.\n" +
//                    "\n" +
//                    "1.영상정보처리기기 설치근거·목적 : < 심야식당 >의\n" +
//                    "\n" +
//                    "2.설치 대수, 설치 위치, 촬영 범위 :\n" +
//                    "설치대수 : 대\n" +
//                    "설치위치 :\n" +
//                    "촬영범위 :\n" +
//                    "3.관리책임자, 담당부서 및 영상정보에 대한 접근권한자 :\n" +
//                    "\n" +
//                    "4.영상정보 촬영시간, 보관기간, 보관장소, 처리방법\n" +
//                    "촬영시간 : 시간\n" +
//                    "보관기간 : 촬영시부터\n" +
//                    "보관장소 및 처리방법 :\n" +
//                    "5.영상정보 확인 방법 및 장소 :\n" +
//                    "\n" +
//                    "6.정보주체의 영상정보 열람 등 요구에 대한 조치 : 개인영상정보 열람.존재확인 청구서로 신청하여야 하며, 정보주체 자신이 촬영된 경우 또는 명백히 정보주체의 생명.신체.재산 이익을 위해 필요한 경우에 한해 열람을 허용함\n" +
//                    "\n" +
//                    "7.영상정보 보호를 위한 기술적.관리적.물리적 조치 :\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제18조(개인정보 처리방침 변경)\n" +
//                    "\n" +
//                    "\n" +
//                    "① 이 개인정보처리방침은 2023년 1월 1부터 적용됩니다.\n" +
//                    "\n" +
//                    "② 이전의 개인정보 처리방침은 아래에서 확인하실 수 있습니다.\n" +
//                    "\n" +
//                    "예시 ) - 20XX. X. X ~ 20XX. X. X 적용 (클릭)\n" +
//                    "\n" +
//                    "예시 ) - 20XX. X. X ~ 20XX. X. X 적용 (클릭)\n" +
//                    "\n" +
//                    "예시 ) - 20XX. X. X ~ 20XX. X. X 적용 (클릭)"
//            , requireContext(), this).show()
//        }
//
//        binding.btnSignupInfo.setOnClickListener {
//            AgreeDialog("< 심야식당 >('https://www.youtube.com/watch?v=4ocGd4-5zbc&t=2008'이하 '심야식당')은(는) 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.\n" +
//                    "\n" +
//                    "○ 이 개인정보처리방침은 2023년 1월 1부터 적용됩니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "제1조(개인정보의 처리 목적)\n" +
//                    "\n" +
//                    "< 심야식당 >('https://www.youtube.com/watch?v=4ocGd4-5zbc&t=2008'이하 '심야식당')은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.\n" +
//                    "\n" +
//                    "1. 홈페이지 회원가입 및 관리\n" +
//                    "\n" +
//                    "회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리, 서비스 부정이용 방지, 만14세 미만 아동의 개인정보 처리 시 법정대리인의 동의여부 확인 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "2. 민원사무 처리\n" +
//                    "\n" +
//                    "민원인의 신원 확인, 민원사항 확인 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "3. 재화 또는 서비스 제공\n" +
//                    "\n" +
//                    "서비스 제공, 콘텐츠 제공, 맞춤서비스 제공, 본인인증을 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "4. 마케팅 및 광고에의 활용\n" +
//                    "\n" +
//                    "신규 서비스(제품) 개발 및 맞춤 서비스 제공, 이벤트 및 광고성 정보 제공 및 참여기회 제공 등을 목적으로 개인정보를 처리합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제2조(개인정보의 처리 및 보유 기간)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.\n" +
//                    "\n" +
//                    "② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.\n" +
//                    "\n" +
//                    "1.<민원사무 처리>\n" +
//                    "<민원사무 처리>와 관련한 개인정보는 수집.이용에 관한 동의일로부터<1년>까지 위 이용목적을 위하여 보유.이용됩니다.\n" +
//                    "보유근거 : 개인\n" +
//                    "관련법령 : 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년\n" +
//                    "예외사유 : 탈퇴\n" +
//                    "\n" +
//                    "\n" +
//                    "제3조(처리하는 개인정보의 항목)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 다음의 개인정보 항목을 처리하고 있습니다.\n" +
//                    "\n" +
//                    "1< 홈페이지 회원가입 및 관리 >\n" +
//                    "필수항목 : 이메일, 비밀번호, 이름\n" +
//                    "선택항목 :\n" +
//                    "\n" +
//                    "\n" +
//                    "제4조(개인정보의 제3자 제공에 관한 사항)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 개인정보를 제1조(개인정보의 처리 목적)에서 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정 등 「개인정보 보호법」 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.\n" +
//                    "\n" +
//                    "② < 심야식당 >은(는) 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.\n" +
//                    "\n" +
//                    "1. < 심야식당 >\n" +
//                    "개인정보를 제공받는 자 : 심야식당\n" +
//                    "제공받는 자의 개인정보 이용목적 : 이메일, 비밀번호, 이름\n" +
//                    "제공받는 자의 보유.이용기간: 1년\n" +
//                    "\n" +
//                    "\n" +
//                    "제5조(개인정보처리의 위탁에 관한 사항)\n" +
//                    "\n" +
//                    "① < 심야식당 >은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.\n" +
//                    "\n" +
//                    "1. < >\n" +
//                    "위탁받는 자 (수탁자) :\n" +
//                    "위탁하는 업무의 내용 :\n" +
//                    "위탁기간 :\n" +
//                    "② < 심야식당 >은(는) 위탁계약 체결시 「개인정보 보호법」 제26조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.\n" +
//                    "\n" +
//                    "③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제6조(개인정보의 파기절차 및 파기방법)\n" +
//                    "\n" +
//                    "\n" +
//                    "① < 심야식당 > 은(는) 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.\n" +
//                    "\n" +
//                    "② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보를 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.\n" +
//                    "1. 법령 근거 :\n" +
//                    "2. 보존하는 개인정보 항목 : 계좌정보, 거래날짜\n" +
//                    "\n" +
//                    "③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.\n" +
//                    "1. 파기절차\n" +
//                    "< 심야식당 > 은(는) 파기 사유가 발생한 개인정보를 선정하고, < 심야식당 > 의 개인정보 보호책임자의 승인을 받아 개인정보를 파기합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제7조(정보주체와 법정대리인의 권리·의무 및 그 행사방법에 관한 사항)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "① 정보주체는 심야식당에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.\n" +
//                    "\n" +
//                    "② 제1항에 따른 권리 행사는심야식당에 대해 「개인정보 보호법」 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 심야식당은(는) 이에 대해 지체 없이 조치하겠습니다.\n" +
//                    "\n" +
//                    "③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다.이 경우 “개인정보 처리 방법에 관한 고시(제2020-7호)” 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.\n" +
//                    "\n" +
//                    "④ 개인정보 열람 및 처리정지 요구는 「개인정보 보호법」 제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.\n" +
//                    "\n" +
//                    "⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.\n" +
//                    "\n" +
//                    "⑥ 심야식당은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제8조(개인정보의 안전성 확보조치에 관한 사항)\n" +
//                    "\n" +
//                    "< 심야식당 >은(는) 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.\n" +
//                    "\n" +
//                    "1. 정기적인 자체 감사 실시\n" +
//                    "개인정보 취급 관련 안정성 확보를 위해 정기적(분기 1회)으로 자체 감사를 실시하고 있습니다.\n" +
//                    "\n" +
//                    "2. 해킹 등에 대비한 기술적 대책\n" +
//                    "<심야식당>('심야식당')은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.\n" +
//                    "\n" +
//                    "3. 문서보안을 위한 잠금장치 사용\n" +
//                    "개인정보가 포함된 서류, 보조저장매체 등을 잠금장치가 있는 안전한 장소에 보관하고 있습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제9조(개인정보를 자동으로 수집하는 장치의 설치·운영 및 그 거부에 관한 사항)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "① 심야식당 은(는) 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.\n" +
//                    "② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.\n" +
//                    "가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.\n" +
//                    "나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.\n" +
//                    "다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "제10조(행태정보의 수집·이용·제공 및 거부 등에 관한 사항)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "행태정보의 수집·이용·제공 및 거부등에 관한 사항\n" +
//                    "\n" +
//                    "<개인정보처리자명>은(는) 온라인 맞춤형 광고 등을 위한 행태정보를 수집·이용·제공하지 않습니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제11조(추가적인 이용·제공 판단기준)\n" +
//                    "\n" +
//                    "< 심야식당 > 은(는) ｢개인정보 보호법｣ 제15조제3항 및 제17조제4항에 따라 ｢개인정보 보호법 시행령｣ 제14조의2에 따른 사항을 고려하여 정보주체의 동의 없이 개인정보를 추가적으로 이용·제공할 수 있습니다.\n" +
//                    "이에 따라 < 심야식당 > 가(이) 정보주체의 동의 없이 추가적인 이용·제공을 하기 위해서 다음과 같은 사항을 고려하였습니다.\n" +
//                    "▶ 개인정보를 추가적으로 이용·제공하려는 목적이 당초 수집 목적과 관련성이 있는지 여부\n" +
//                    "\n" +
//                    "▶ 개인정보를 수집한 정황 또는 처리 관행에 비추어 볼 때 추가적인 이용·제공에 대한 예측 가능성이 있는지 여부\n" +
//                    "\n" +
//                    "▶ 개인정보의 추가적인 이용·제공이 정보주체의 이익을 부당하게 침해하는지 여부\n" +
//                    "\n" +
//                    "▶ 가명처리 또는 암호화 등 안전성 확보에 필요한 조치를 하였는지 여부\n" +
//                    "\n" +
//                    "※ 추가적인 이용·제공 시 고려사항에 대한 판단기준은 사업자/단체 스스로 자율적으로 판단하여 작성·공개함\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제12조(가명정보를 처리하는 경우 가명정보 처리에 관한 사항)\n" +
//                    "\n" +
//                    "< 심야식당 > 은(는) 다음과 같은 목적으로 가명정보를 처리하고 있습니다.\n" +
//                    "\n" +
//                    "▶ 가명정보의 처리 목적\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명정보의 처리 및 보유기간\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명정보의 제3자 제공에 관한 사항(해당되는 경우에만 작성)\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명정보 처리의 위탁에 관한 사항(해당되는 경우에만 작성)\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 가명처리하는 개인정보의 항목\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "▶ 법 제28조의4(가명정보에 대한 안전조치 의무 등)에 따른 가명정보의 안전성 확보조치에 관한 사항\n" +
//                    "\n" +
//                    "- 직접작성 가능합니다.\n" +
//                    "\n" +
//                    "제13조 (개인정보 보호책임자에 관한 사항)\n" +
//                    "\n" +
//                    "① 심야식당 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.\n" +
//                    "\n" +
//                    "▶ 개인정보 보호책임자\n" +
//                    "성명 :심야식당\n" +
//                    "직책 :UMC\n" +
//                    "직급 :3기\n" +
//                    "연락처 :010-0000-0000, abc123@naver.com, 000-000-0000\n" +
//                    "※ 개인정보 보호 담당부서로 연결됩니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "▶ 개인정보 보호 담당부서\n" +
//                    "부서명 :\n" +
//                    "담당자 :\n" +
//                    "연락처 :, ,\n" +
//                    "② 정보주체께서는 심야식당 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 심야식당 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제14조(국내대리인의 지정)\n" +
//                    "\n" +
//                    "정보주체는 ｢개인정보 보호법｣ 제39조의11에 따라 지정된 < 심야식당 >의 국내대리인에게 개인정보 관련 고충처리 등의 업무를 위하여 연락을 취할 수 있습니다. < 심야식당 >은(는) 정보주체의 개인정보 관련 고충처리 등 개인정보 보호책임자의 업무 등을 신속하게 처리할 수 있도록 노력하겠습니다.\n" +
//                    "\n" +
//                    "▶ < 심야식당 > 은(는) ｢개인정보 보호법｣ 제39조의11에 따라 국내대리인을 지정하였습니다.\n" +
//                    "\n" +
//                    "- 국내대리인의 성명 : [대리인 성명_직접입력] (법인의 경우 법인명, 대표자의 성명)\n" +
//                    "\n" +
//                    "- 국내대리인의 주소 : [대리인 주소_직접입력] (법인의 경우 영업소 소재지)\n" +
//                    "\n" +
//                    "- 국내대리인의 전화번호 : [대리인 전화번호_직접입력]\n" +
//                    "\n" +
//                    "- 국내대리인의 전자우편 주소 : [대리인 전자우편_직접입력]\n" +
//                    "\n" +
//                    "제15조(개인정보의 열람청구를 접수·처리하는 부서)\n" +
//                    "정보주체는 ｢개인정보 보호법｣ 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다.\n" +
//                    "< 심야식당 >은(는) 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다.\n" +
//                    "\n" +
//                    "▶ 개인정보 열람청구 접수·처리 부서\n" +
//                    "부서명 : 심야식당\n" +
//                    "담당자 : 심야식당\n" +
//                    "연락처 : , ,\n" +
//                    "\n" +
//                    "\n" +
//                    "제16조(정보주체의 권익침해에 대한 구제방법)\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회, 한국인터넷진흥원 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기 바랍니다.\n" +
//                    "\n" +
//                    "1. 개인정보분쟁조정위원회 : (국번없이) 1833-6972 (www.kopico.go.kr)\n" +
//                    "2. 개인정보침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)\n" +
//                    "3. 대검찰청 : (국번없이) 1301 (www.spo.go.kr)\n" +
//                    "4. 경찰청 : (국번없이) 182 (ecrm.cyber.go.kr)\n" +
//                    "\n" +
//                    "「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의 정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대 하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.\n" +
//                    "\n" +
//                    "※ 행정심판에 대해 자세한 사항은 중앙행정심판위원회(www.simpan.go.kr) 홈페이지를 참고하시기 바랍니다.\n" +
//                    "\n" +
//                    "제17조(영상정보처리기기 운영·관리에 관한 사항)\n" +
//                    "① < 심야식당 >은(는) 아래와 같이 영상정보처리기기를 설치·운영하고 있습니다.\n" +
//                    "\n" +
//                    "1.영상정보처리기기 설치근거·목적 : < 심야식당 >의\n" +
//                    "\n" +
//                    "2.설치 대수, 설치 위치, 촬영 범위 :\n" +
//                    "설치대수 : 대\n" +
//                    "설치위치 :\n" +
//                    "촬영범위 :\n" +
//                    "3.관리책임자, 담당부서 및 영상정보에 대한 접근권한자 :\n" +
//                    "\n" +
//                    "4.영상정보 촬영시간, 보관기간, 보관장소, 처리방법\n" +
//                    "촬영시간 : 시간\n" +
//                    "보관기간 : 촬영시부터\n" +
//                    "보관장소 및 처리방법 :\n" +
//                    "5.영상정보 확인 방법 및 장소 :\n" +
//                    "\n" +
//                    "6.정보주체의 영상정보 열람 등 요구에 대한 조치 : 개인영상정보 열람.존재확인 청구서로 신청하여야 하며, 정보주체 자신이 촬영된 경우 또는 명백히 정보주체의 생명.신체.재산 이익을 위해 필요한 경우에 한해 열람을 허용함\n" +
//                    "\n" +
//                    "7.영상정보 보호를 위한 기술적.관리적.물리적 조치 :\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "제18조(개인정보 처리방침 변경)\n" +
//                    "\n" +
//                    "\n" +
//                    "① 이 개인정보처리방침은 2023년 1월 1부터 적용됩니다.\n" +
//                    "\n" +
//                    "② 이전의 개인정보 처리방침은 아래에서 확인하실 수 있습니다.\n" +
//                    "\n" +
//                    "예시 ) - 20XX. X. X ~ 20XX. X. X 적용 (클릭)\n" +
//                    "\n" +
//                    "예시 ) - 20XX. X. X ~ 20XX. X. X 적용 (클릭)\n" +
//                    "\n" +
//                    "예시 ) - 20XX. X. X ~ 20XX. X. X 적용 (클릭)", requireContext(), this).show()
//        }
//    }

    override fun onCloseButtonClicked() {
    }
}