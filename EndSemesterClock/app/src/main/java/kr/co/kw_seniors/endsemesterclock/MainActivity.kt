package kr.co.kw_seniors.endsemesterclock

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.hanbit.base.BaseActivity
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    companion object{
        const val PERM_INTERNET = 101
    }

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 인터넷 권한 요청
        requirePermissions(arrayOf(Manifest.permission.INTERNET), PERM_INTERNET)

        // 공지 버튼 리스너
        binding.btnNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
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
        when(requestCode){
            PERM_INTERNET -> {
                Toast.makeText(baseContext,
                    "인터넷 접근 권한을 승인해야 앱을 사용할 수 있습니다.",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
    /* professor 버튼기능

fun main() {

    var btn: ImageButton = findViewById(R.id.btnProfessor)

    btn.setOnClickListener{
        main2()
    }
}

fun main2() {
    setContentView(R.layout.activity_professor)
}   */
}