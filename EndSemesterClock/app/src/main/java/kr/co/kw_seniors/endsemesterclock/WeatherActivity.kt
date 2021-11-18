package kr.co.kw_seniors.endsemesterclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    // 레이아웃 바인딩
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}