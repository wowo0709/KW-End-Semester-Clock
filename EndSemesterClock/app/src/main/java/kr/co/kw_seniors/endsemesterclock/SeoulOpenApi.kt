package kr.co.kw_seniors.endsemesterclock

import kr.co.kw_seniors.endsemesterclock.data.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object{
        val DOMAIN = "http://openAPI.seoul.go.kr:8088/"
        val API_KEY = "724575495673616e37307577486970"
    }
}
interface SeoulOpenService{
    @GET("{api_key}/json/LOCALDATA_072404_NW/1/1000")
    fun getRestaurant(@Path("api_key") key: String): Call<Restaurant>
}