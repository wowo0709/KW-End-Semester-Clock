package kr.co.kw_seniors.endsemesterclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityWeatherBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val num_of_rows = 10
val page_no = 1
val data_type = "JSON"
val base_time = 1100
val base_data = 20200808
val nx = "55"
val ny = "127"

interface WeatherInterface {
    @GET("getVilageFcst?serviceKey=gpTVgeb7HcaCG4gXz" +
            "JnrHSbEtonWGwuI2a7wgrCHSqYxnq9xewWyjZAdNZbucgw" +
            "%2FuStJigg6KeHJdxo7OqqBDg%3D%3D")
    fun GetWeather(
        @Query("dataType") data_type : String,
        @Query("numOfRows") num_of_rows : Int,
        @Query("pageNo") page_no : Int,
        @Query("base_date") base_date : Int,
        @Query("base_time") base_time : Int,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ): Call<WEATHER> // WEATHER는 DATA CLASS
}

data class WEATHER (
    val response : RESPONSE
)
data class RESPONSE (
    val header : HEADER,
    val body : BODY
)
data class HEADER(
    val resultCode : Int,
    val resultMsg : String
)
data class BODY(
    val dataType : String,
    val items : ITEMS
)
data class ITEMS(
    val item : List<ITEM>
)
data class ITEM(
    val baseData : Int,
    val category : String
)

private val retrofit = Retrofit.Builder()
    .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiObject {
    //val retval baseTime : Int,
    val retrofitService: WeatherInterface by lazy {
        retrofit.create(WeatherInterface::class.java)
    }
}


class WeatherActivity : AppCompatActivity() {
    // 레이아웃 바인딩
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //
        val call = ApiObject.retrofitService.GetWeather(data_type, num_of_rows, page_no, base_data, base_time, nx, ny)
        call.enqueue(object : retrofit2.Callback<WEATHER>{
            override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                if (response.isSuccessful){
                    Log.d("api", response.body().toString())
                    Log.d("api", response.body()!!.response.body.items.item.toString())
                    Log.d("api", response.body()!!.response.body.items.item[0].category)
                }
            }
            override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                Log.d("api fail : ", t.message!!)
            }
        })
    }
}
