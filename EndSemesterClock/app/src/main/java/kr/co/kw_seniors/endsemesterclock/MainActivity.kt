package kr.co.kw_seniors.endsemesterclock

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.hanbit.base.BaseActivity
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import android.widget.TextView
import kotlin.concurrent.timer

class MainActivity : BaseActivity() {

    companion object{
        const val PERM_INTERNET = 101

        // 재크롤링 방지 : 0 - 현재 크롤링 x , 1 - 크롤링 o
        var NoticeCrawling = 0
        var ProfessorCrawling = 0   // 초기 설정은 크롤링이 안된 상태

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")    // 데이터 포멧
        val End = dateFormat.parse("2021-12-21 12:00:00").time      // default 종강 시간
        val Now = Calendar.getInstance().apply{}.time.time  // 지금 시간
        var dDay = End-Now  // 남은 시간 계산
        var dDayFormat = "${dDay/(24*60*60*1000)}일\n${(dDay/(60*60*1000))%24}시간 ${(dDay/(60*1000))%60}분 ${(dDay/1000)%60}초" // 출력 포멧
    }

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 인터넷 권한 요청
        requirePermissions(arrayOf(Manifest.permission.INTERNET), PERM_INTERNET)

        binding.textClock.setText(dDayFormat)   // 남은 시간 세팅
        startTimer()    // 타이머 시작

        // 공지 버튼 리스너
        binding.btnNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }
        // 교수 버튼 리스너
        binding.btnProfessor.setOnClickListener {
            val intent = Intent(this, ProfessorActivity::class.java)
            startActivity(intent)
        }
        // 날씨 버튼 리스너
        binding.btnWeather.setOnClickListener {
            // TODO: 날씨 액티비티 연결
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
        // 음식점 지도 버튼 리스너
        binding.btnMeal.setOnClickListener {
            val intent = Intent(this, GoogleMapActivity::class.java)
            startActivity(intent)
        }

    }

    // 권한 허용 시
    override fun permissionGranted(requestCode: Int) {
        when(requestCode){
            PERM_INTERNET -> {
                // nothing
            }
        }
    }
    // 권한 거부 시
    override fun permissionDenied(requestCode: Int) {
        when (requestCode) {
            PERM_INTERNET -> {
                Toast.makeText(
                    baseContext,
                    "인터넷 접근 권한을 승인해야 앱을 사용할 수 있습니다.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun startTimer(){
        timer(period = 1000){
            dDay-=1000  // 1초씩 감소
            dDayFormat = "${dDay/(24*60*60*1000)}일\n${(dDay/(60*60*1000))%24}시간 ${(dDay/(60*1000))%60}분 ${(dDay/1000)%60}초"
            runOnUiThread{
                binding.textClock.setText(dDayFormat)   // 남은 시간 세팅
            }
        }
    }
}