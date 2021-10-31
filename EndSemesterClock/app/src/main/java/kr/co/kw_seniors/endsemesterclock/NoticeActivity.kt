package kr.co.kw_seniors.endsemesterclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityNoticeBinding
import org.jsoup.Jsoup

class NoticeActivity : AppCompatActivity() {

    companion object{
        const val COMMON_NOTICE_URL = "https://www.kw.ac.kr/ko/life/notice.jsp?srCategoryId=0&mode=list&searchKey=1&searchVal="   // 일반 공지
        const val BACHELOR_NOTICE_URL = "https://www.kw.ac.kr/ko/life/notice.jsp?srCategoryId=1&mode=list&searchKey=1&searchVal=" // 학사 공지
        const val STUDENT_NOTICE_URL = "https://www.kw.ac.kr/ko/life/notice.jsp?srCategoryId=2&mode=list&searchKey=1&searchVal="  // 학생 공지
        const val ENROLL_NOTICE_URL = "https://www.kw.ac.kr/ko/life/notice.jsp?srCategoryId=4&mode=list&searchKey=1&searchVal="   // 등록/장학 공지
    }

    val binding by lazy{ActivityNoticeBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // html 문서 가져오기
        var commonNoticeHtml = Jsoup.connect(COMMON_NOTICE_URL).get()
        var bachelorNoticeHtml = Jsoup.connect(BACHELOR_NOTICE_URL).get()
        var studentNoticeHtml = Jsoup.connect(STUDENT_NOTICE_URL).get()
        var enrollNoticeHtml = Jsoup.connect(ENROLL_NOTICE_URL).get()
    }
}