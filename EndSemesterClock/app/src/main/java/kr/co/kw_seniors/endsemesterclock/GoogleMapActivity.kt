package kr.co.kw_seniors.endsemesterclock

import android.content.Intent
import android.location.Address
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import kr.co.kw_seniors.endsemesterclock.data.Restaurant
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityGoogleMapBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.floor
import android.location.Geocoder as Geocoder
import kr.hyosang.coordinate.*

class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityGoogleMapBinding
    // GoogleMap 객체
    private lateinit var mMap: GoogleMap
    // 위치좌표 집합
    val latLngBounds = LatLngBounds.Builder()
    // 마커 클러스터 매니저
    private lateinit var clusterManager: ClusterManager<MyItem>


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

        loadRestaurants()

        setCamera()

    }
    // 음식점 데이터 불러오기
    fun loadRestaurants(){
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val seoulOpenService = retrofit.create(SeoulOpenService::class.java)
        for (n in 1..140) {
            Log.d("page_num","$n")
            seoulOpenService
                .getRestaurant(SeoulOpenApi.API_KEY, "${100*(n-1)+1}", "${100*n}")
                .enqueue(object : Callback<Restaurant> {
                    override fun onResponse(
                        call: Call<Restaurant>,
                        response: Response<Restaurant>
                    ) {
                        Log.d("loadRestaurants", "${response.body()}")
                        showRestaurants(response.body() as Restaurant)
                    }

                    override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                        Toast.makeText(
                            baseContext, "서버에서 데이터를 가져올 수 없습니다.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

    }
    // 음식점 위치를 마커로 표시하기
    fun showRestaurants(restaurants: Restaurant){
        // val geocoder = Geocoder(this)
        // var coor: MutableList<Address>
        var tmPt: CoordPoint
        var wgsPt: CoordPoint
        var latitude: Double
        var longitude: Double
        var position: LatLng
        var marker: MarkerOptions
        var i = 0
        for (rest in restaurants.LOCALDATA_072404_NW.row){
            i += 1
            Log.d("showRestaurant", "$i: " + rest.BPLCNM)
            // 주소를 좌표로 변환
            // coor = geocoder.getFromLocationName(rest.SITEWHLADDR, 1)
            // 위도-경도 좌표
            // position = LatLng(coor[0].latitude, coor[0].longitude)
            if(rest.X=="" || rest.Y=="" || rest.TRDSTATENM == "폐업")
                continue
            Log.i("ktcoor", "latitude: ${rest.X} longitude: ${rest.Y}")
            tmPt = CoordPoint(rest.X.toDouble(),rest.Y.toDouble())
            wgsPt = TransCoord.getTransCoord(tmPt, TransCoord.COORD_TYPE_TM,TransCoord.COORD_TYPE_WGS84)
            latitude = wgsPt.y
            longitude = wgsPt.x
            Log.i("wgscoor", "latitude: ${latitude} longitude: ${longitude}")


            position = LatLng(latitude, longitude)
            // 마커 생성
            marker = MarkerOptions().position(position).title(rest.BPLCNM)// 사업장명
            mMap.addMarker(marker)
            // 카메라 위치 조정
            // latLngBounds.include(marker.position)
        }
        // 광운대 좌표: 37.6194, 127.0598
//        val bounds = latLngBounds.build()
//        val padding = 15
//        val updated = CameraUpdateFactory.newLatLngBounds(bounds, padding)
//        mMap.moveCamera(updated)

//        val LATLNG = LatLng(37.6194, 127.0598)
//        val cameraPosition = CameraPosition.Builder()
//            .target(LATLNG)
//            .zoom(16.0f)
//            .build()
//        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
//        mMap.moveCamera(cameraUpdate)
    }

    // 카메라 설정
    fun setCamera(){
        val LATLNG = LatLng(37.6194, 127.0598)
        val cameraPosition = CameraPosition.Builder()
            .target(LATLNG)
            .zoom(16.0f)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap.moveCamera(cameraUpdate)

        // 마커 클러스터링
        clusterManager = ClusterManager(this, mMap)
        mMap.setOnCameraIdleListener(clusterManager)
        mMap.setOnMarkerClickListener(clusterManager)

        addItems()
    }

    // 마커 아이템 추가
    private fun addItems() {

        // Set some lat/lng coordinates to start with.
        var lat = 51.5145160
        var lng = -0.1270060

        // Add ten cluster items in close proximity, for purposes of this example.
        for (i in 0..9) {
            val offset = i / 60.0
            lat += offset
            lng += offset
            val offsetItem =
                MyItem(lat, lng, "Title $i", "Snippet $i")
            clusterManager.addItem(offsetItem)
        }
    }

    inner class MyItem(
        lat: Double,
        lng: Double,
        title: String,
        snippet: String
    ): ClusterItem{

        private val position: LatLng
        private val title: String
        private val snippet: String

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String? {
            return title
        }

        override fun getSnippet(): String? {
            return snippet
        }

        init{
            position = LatLng(lat, lng)
            this.title = title
            this.snippet = snippet
        }
    }
}