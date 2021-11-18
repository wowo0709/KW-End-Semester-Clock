package kr.co.kw_seniors.endsemesterclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kr.co.hanbit.base.BaseActivity
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityGoogleMapBinding

class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityGoogleMapBinding
    // GoogleMap 객체
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // id가 map인 SupportMapFragment를 찾은 후 getMapAsync()를 호출해서
        // 안드로이드에 구글 지도를 그려달라는 요청
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    // 구글 지도가 준비되면 안드로이드가 OnMapReadyCallback 인터페이스의 onMapReady() 메서드를
    // 호출하면서 파라미터로 준비된 GoogleMap을 전달
    override fun onMapReady(googleMap: GoogleMap) {
        // 메서드 안에서 미리 선언된 mMap 프로퍼티에 GoogleMap을 저장해두면 액티비티 전체에서 맵을 사용 가능
        mMap = googleMap



    }
}