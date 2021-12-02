package kr.co.kw_seniors.endsemesterclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.annotations.SerializedName
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherActivity : AppCompatActivity() {

    companion object{
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "745cc7df93b0641772ea3cd85925e2cb"//https://home.openweathermap.org 에서의 키값
        var lat = "37.6444" //위도 좌표
        var lon = "127.1444" //경도 좌표
    }

    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Create Retrofit Builder
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("MainActivity", "result :" + t.message)
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.code() == 200){
                    val weatherResponse = response.body()
                    Log.d("MainActivity", "result: " + weatherResponse.toString())
                    var cTemp =  weatherResponse!!.main!!.temp.toInt() - 273  //켈빈을 섭씨로 변환
                    var minTemp = weatherResponse!!.main!!.temp_min.toInt() - 273
                    var maxTemp = weatherResponse!!.main!!.temp_max.toInt() - 273
                    var hum = weatherResponse!!.main!!.humidity.toInt()//습도
                    
                    var bigString = binding.tvtext//우아한 형제들 문구 관련 텍스트뷰

                    binding.tvtem.text=cTemp.toString()
                    binding.tvhigh.text="최고 온도:"+maxTemp.toString()
                    binding.tvlow.text="최저 온도:"+minTemp.toString()
                    
                    
                    if (hum > 90){
                        bigString.text = "찝찝하다" // 습도 높을 때
                    }else if(cTemp > 25){
                        bigString.text = "쨍쨍하다" // 더울 때
                    }else if(cTemp > 10){
                        bigString.text = "날씨좋다" // 평범하다
                    }else{
                        bigString.text = "껴입어라" // 춥다
                    }
                }
            }

        })
    }
}

interface WeatherService{

    @GET("data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String) :
            Call<WeatherResponse>
}

class WeatherResponse(){
    @SerializedName("weather") var weather = ArrayList<Weather>()
    @SerializedName("main") var main: Main? = null
    @SerializedName("wind") var wind : Wind? = null
    @SerializedName("sys") var sys: Sys? = null
}

class Weather {
    @SerializedName("id") var id: Int = 0
    @SerializedName("main") var main : String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("icon") var icon : String? = null
}

class Main {
    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat()
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat()
    @SerializedName("temp_min")
    var temp_min: Float = 0.toFloat()
    @SerializedName("temp_max")
    var temp_max: Float = 0.toFloat()

}

class Wind {
    @SerializedName("speed")
    var speed: Float = 0.toFloat()
    @SerializedName("deg")
    var deg: Float = 0.toFloat()
}

class Sys {
    @SerializedName("country")
    var country: String? = null
    @SerializedName("sunrise")
    var sunrise: Long = 0
    @SerializedName("sunset")
    var sunset: Long = 0
}
