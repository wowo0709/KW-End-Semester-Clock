package kr.co.kw_seniors.endsemesterclock

import android.content.Context
import android.icu.text.Transliterator
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kr.co.kw_seniors.endsemesterclock.ProfessorActivity.UrlRun
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityProfessorBinding
import org.jsoup.Jsoup

class ProfessorActivity : AppCompatActivity() {

    companion object {
        val URL: Array<String> = arrayOf<String>(
            // 전자공학과
            "https://www.kw.ac.kr/ko/univ/electronic01_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic01_2.jsp?MaxRows=10&tpage=2",
            "https://www.kw.ac.kr/ko/univ/electronic01_2.jsp?MaxRows=10&tpage=3",
            // 전자통신공학과
            "https://www.kw.ac.kr/ko/univ/electronic02_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic02_2.jsp?MaxRows=10&tpage=2",
            // 전자융합공학과
            "https://www.kw.ac.kr/ko/univ/electronic03_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic03_2.jsp?MaxRows=10&tpage=2",
            // 전기공학과
            "https://www.kw.ac.kr/ko/univ/electronic06_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic06_2.jsp?MaxRows=10&tpage=2",
            // 전자재료공학과
            "https://www.kw.ac.kr/ko/univ/electronic07_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic07_2.jsp?MaxRows=10&tpage=2",
            // 로봇학부:정보제어전공
            "https://www.kw.ac.kr/ko/univ/electronic08_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic08_2.jsp?MaxRows=10&tpage=2",
            // 로봇학부:지능시스템전공
            "https://www.kw.ac.kr/ko/univ/electronic09_2.jsp?MaxRows=10&tpage=1",
            // 소프트웨어학부
            "https://www.kw.ac.kr/ko/univ/electronic05_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic05_2.jsp?MaxRows=10&tpage=2",
            // 컴퓨터정보공학부
            "https://www.kw.ac.kr/ko/univ/electronic04_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/electronic04_2.jsp?MaxRows=10&tpage=2",
            // 정보융합학부
            "https://www.kw.ac.kr/ko/univ/convergence03_2.jsp?MaxRows=10&tpage=1",
            // 건축공학과
            "https://www.kw.ac.kr/ko/univ/engineering01_2.jsp?MaxRows=10&tpage=1",
            // 화학공학과
            "https://www.kw.ac.kr/ko/univ/engineering02_2.jsp?MaxRows=10&tpage=1",
            // 환경공학과
            "https://www.kw.ac.kr/ko/univ/engineering03_2.jsp?MaxRows=10&tpage=1",
            // 건축학과
            "https://www.kw.ac.kr/ko/univ/engineering04_2.jsp?MaxRows=10&tpage=1",
            // 수학과
            "https://www.kw.ac.kr/ko/univ/science01_2.jsp?MaxRows=10&tpage=1",
            // 전자바이오물리학과
            "https://www.kw.ac.kr/ko/univ/science02_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/science02_2.jsp?MaxRows=10&tpage=2",
            // 화학과
            "https://www.kw.ac.kr/ko/univ/science03_2.jsp?MaxRows=10&tpage=1",
            // 스포츠융합학과
            "https://www.kw.ac.kr/ko/univ/science04_2.jsp?MaxRows=10&tpage=1",
            // 정보콘텐츠학과(야)
            "https://www.kw.ac.kr/ko/univ/science05_2.jsp?MaxRows=10&tpage=1",
            // 국어국문학과
            "https://www.kw.ac.kr/ko/univ/social01_2.jsp?MaxRows=10&tpage=1",
            // 영어산업학과
            "https://www.kw.ac.kr/ko/univ/social02_2.jsp?MaxRows=10&tpage=1",
            // 미디어커뮤니케이션학부:미디어엔터테인먼트전공
            "https://www.kw.ac.kr/ko/univ/social03_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/social03_2.jsp?MaxRows=10&tpage=2",
            // 미디어커뮤니케이션학부:인터랙티브미디어커뮤니케이션전공
            "https://www.kw.ac.kr/ko/univ/social04_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/social04_2.jsp?MaxRows=10&tpage=2",
            // 미디어커뮤니케이션학부:전략커뮤니케이션전공
            "https://www.kw.ac.kr/ko/univ/social05_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/social05_2.jsp?MaxRows=10&tpage=2",
            // 산업심리학과
            "https://www.kw.ac.kr/ko/univ/social06_2.jsp?MaxRows=10&tpage=1",
            // 동북아문화산업부:문화교류전공
            "https://www.kw.ac.kr/ko/univ/social07_2.jsp?MaxRows=10&tpage=1",
            // 동북아문화산업부:문화콘텐츠개발전공
            "https://www.kw.ac.kr/ko/univ/social08_2.jsp?MaxRows=10&tpage=1",
            // 행정학과
            "https://www.kw.ac.kr/ko/univ/law01_2.jsp?MaxRows=10&tpage=1",
            // 법학부:일반법학전공
            "https://www.kw.ac.kr/ko/univ/law02_2.jsp?MaxRows=10&tpage=1",
            // 법학부:국제법무전공
            "https://www.kw.ac.kr/ko/univ/law03_2.jsp?MaxRows=10&tpage=1",
            // 법학부:과학기술법무전공
            "https://www.kw.ac.kr/ko/univ/law04_2.jsp?MaxRows=10&tpage=1",
            // 국제학부:국제지역전공
            "https://www.kw.ac.kr/ko/univ/law05_2.jsp?MaxRows=10&tpage=1",
            // 자산관리학과(야)
            "https://www.kw.ac.kr/ko/univ/law07_2.jsp?MaxRows=10&tpage=1",
            // 경영학부:경영학전공
            "https://www.kw.ac.kr/ko/univ/business01_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/business01_2.jsp?MaxRows=10&tpage=2",
            "https://www.kw.ac.kr/ko/univ/business01_2.jsp?MaxRows=10&tpage=3",
            // 국제통상학부:국제통상전공
            "https://www.kw.ac.kr/ko/univ/business02_2.jsp?MaxRows=10&tpage=1",
            // 인제니움학부대학
            "https://www.kw.ac.kr/ko/univ/ingenium01_2.jsp?MaxRows=10&tpage=1",
            "https://www.kw.ac.kr/ko/univ/ingenium01_2.jsp?MaxRows=10&tpage=2",
            "https://www.kw.ac.kr/ko/univ/ingenium01_2.jsp?MaxRows=10&tpage=3"
        )
        // HTML 문서 내에서 공지사항 아이템 태그의 경로
        const val ITEM_ROUTE = "div.table-scroll-box form table tbody tr"
    }

    // 레이아웃 바인딩
    val binding by lazy { ActivityProfessorBinding.inflate(layoutInflater) }

    // RoomHelper
    var helper: RoomHelper? = null

    // 리사이클러 뷰 어댑터
    lateinit var adapter: ProfessorRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // RoomHelper 생성
        helper = Room.databaseBuilder(this, RoomHelper::class.java, "professor_item")
            .allowMainThreadQueries().build()

        // 웹 크롤링
        for (i in 0 until URL.size) { // 각 카테고리에 대해,
                var thread: Thread
                thread = Thread(UrlRun(ProfessorActivity.URL[i],i,applicationContext))
                // 스레드 실행
                thread.start()
                // 웹 크롤링 스레드가 끝날 때까지 메인 스레드 대기
                thread.join()
        }
        Log.d("Professor/OnCreate", "웹 크롤링 완료")

        var data: MutableList<ProfessorItem>

        // 스피너 선언
        val spinnerprofessor1 = findViewById<Spinner>(R.id.spinnerprofessor1)   // 단대 스피너
        val spinnerprofessor2 = findViewById<Spinner>(R.id.spinnerprofessor2)   // 학과 스피너

        //val temptext = findViewById<TextView>(R.id.temp)        // test용 코드

        // 어뎁터 설정
        spinnerprofessor1.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_단과대,
            android.R.layout.simple_spinner_item
        )

        // 초기설정
        data = loadData("전자공학과")
        setData(data)
        // 단대 스피너 리스너
        spinnerprofessor1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                Position: Int,
                id: Long
            ) {
                when (Position) {
                    // 전자정보공과대학
                    0 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_전자정보공과대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("전자공학과")
                                        1 -> data = loadData("전자통신공학과")
                                        2 -> data = loadData("전자융합공학과")
                                        3 -> data = loadData("전기공학과")
                                        4 -> data = loadData("전자재료공학과")
                                        5 -> data = loadData("로봇학부정보제어전공")
                                        6 -> data = loadData("로봇학부지능시스템전공")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 소프트웨어융합대학
                    1 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_소프트웨어융합대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("소프트웨어학부")
                                        1 -> data = loadData("컴퓨터정보공학부")
                                        2 -> data = loadData("정보융합학부")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 공과대학
                    2 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_공과대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("건축공학과")
                                        1 -> data = loadData("화학공학과")
                                        2 -> data = loadData("환경공학과")
                                        3 -> data = loadData("건축학과")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 자연과학대학
                    3 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_자연과학대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("수학과")
                                        1 -> data = loadData("전자바이오물리학과")
                                        2 -> data = loadData("화학과")
                                        3 -> data = loadData("스포츠융합학과")
                                        4 -> data = loadData("정보콘텐츠학과(야)")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 인문사회과학대학
                    4 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_인문사회과학대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("국어국문학과")
                                        1 -> data = loadData("영어산업학과")
                                        2 -> data = loadData("미디어커뮤니케이션학부미디어엔터테인먼트전공")
                                        3 -> data = loadData("미디어커뮤니케이션학부인터랙티브미디어커뮤니케이션전공")
                                        4 -> data = loadData("미디어커뮤니케이션학부전략커뮤니케이션전공")
                                        5 -> data = loadData("산업심리학과")
                                        6 -> data = loadData("동북아문화산업부문화교류전공")
                                        7 -> data = loadData("동북아문화산업부문화콘텐츠개발전공")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 정책법학대학
                    5 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_정책법학대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("행정학과")
                                        1 -> data = loadData("법학부일반법학전공")
                                        2 -> data = loadData("법학부국제법무전공")
                                        3 -> data = loadData("법학부과학기술법무전공")
                                        4 -> data = loadData("국제학부국제지역전공")
                                        5 -> data = loadData("자산관리학과(야)")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 경영대학
                    6 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_경영대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("경영학부경영학전공")
                                        1 -> data = loadData("국제통상학부국제통상전공")
                                    }
                                    setData(data)
                                }
                            }
                    }
                    // 인제니움학부
                    7 -> {
                        spinnerprofessor2.adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(),
                            R.array.array_인제니움학부대학,
                            android.R.layout.simple_spinner_item
                        )
                        spinnerprofessor2.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> data = loadData("인제니움학부대학")
                                    }
                                    setData(data)
                                }
                            }
                    }

                }
            }
        }

    }

    private fun setData(data: List<ProfessorItem>) {
        adapter = ProfessorRecyclerAdapter()
        adapter.listData.addAll(data ?: listOf())
        adapter.notifyDataSetChanged()
        binding.recyclerViewProfessor.adapter = adapter
        binding.recyclerViewProfessor.layoutManager = LinearLayoutManager(this)
    }

    // Room에 저장된 아이템 리스트 불러오기
    private fun loadData(category: String): MutableList<ProfessorItem> {
        var data: MutableList<ProfessorItem> = mutableListOf()

        // TODO: Room의 데이터 가져오기
        data = helper?.professorItemDAO()?.getCategoryData(category)!!

        return data
    }


    // 웹 크롤링 스레드 클래스
    inner class UrlRun(var url: String, var pages: Int, var context: Context) : Runnable {
        // TODO: 아이템 양식 정의
        // lateinit var items: Items
        @Synchronized
        override fun run() {
            try {
                // html 문서 가져오기
                val professorHtml = Jsoup.connect(url).get()
                // 교수님 정보 가져오기
                val items = professorHtml.select(ProfessorActivity.ITEM_ROUTE)
                // 가져온 아이템들을 양식에 맞게 저장
                var category = professorHtml.select("div.h2-title-box h2").text()   // 학과
                // 가져올 정보
                var name: String        // 이름
                var position: String    // 직위
                var telNum: String      // 교내전화
                var email: String       // 이메일
                var homepage: String    // 홈페이지 주소
                for (item in items) {
                    // 파싱
                    name = item.select("td")[1].text()
                    position = item.select("td")[2].text()
                    telNum = item.select("td")[3].text()
                    email = item.select("td")[4].text()
                    homepage = item.select("td")[5].text()

                    val professorItem = ProfessorItem(category, name,position, telNum,email, homepage)
                    helper?.professorItemDAO()?.insert(professorItem)

                    Log.i(
                        "Professor/UrlRun",
                        "$category, $name, ${position}, $telNum, $email, $homepage"
                    )
                }

            } catch (e: Exception) {
                Log.e("NoticeActivity/UrlRun", e.toString())
            }
        }

    }
}