package kr.co.kw_seniors.endsemesterclock

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.hanbit.base.BaseActivity
import kr.co.kw_seniors.endsemesterclock.data.Restaurant
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityGoogleMapBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        mMap.setOnMarkerClickListener {
            if(it.tag != null){
                var url = it.tag as String
                if(!url.startsWith("http")){
                    url = "http://${url}"
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
            true
        }

        loadRestaurants()

    }
    // 음식점 데이터 불러오기
    fun loadRestaurants(){
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val seoulOpenService = retrofit.create(SeoulOpenService::class.java)
        seoulOpenService
            .getRestaurant(SeoulOpenApi.API_KEY)
            .enqueue(object: Callback<Restaurant>{
                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                    showRestaurants(response.body() as Restaurant)
                }

                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.",
                                    Toast.LENGTH_LONG).show()
                }

            })
    }
    // 음식점 위치를 마커로 표시하기
    fun showRestaurants(restaurants: Restaurant){
        val geocoder = Geocoder(this)
        var coor: MutableList<Address>
        var position: LatLng
        var marker: MarkerOptions
        val latLngBounds = LatLngBounds.Builder()
        for (rest in restaurants.nowonRestaurants.row){
            // 주소를 좌표로 변환
            coor = geocoder.getFromLocationName(rest.SITEWHLADDR, 1)
            // 위도-경도 좌표
            position = LatLng(coor[0].latitude, coor[0].longitude)
            // 마커 생성
            marker = MarkerOptions().position(position).title(rest.BPLCNM) // 사업장명
            var obj = mMap.addMarker(marker)
            obj.tag = rest.HOMEPAGE
            // 카메라 위치 조정
            latLngBounds.include(marker.position)
        }
        val bounds = latLngBounds.build()
        val padding = 5
        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mMap.moveCamera(updated)
    }
}