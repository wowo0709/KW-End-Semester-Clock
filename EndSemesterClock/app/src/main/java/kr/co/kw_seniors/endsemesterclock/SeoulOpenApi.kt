package kr.co.kw_seniors.endsemesterclock

import kr.co.kw_seniors.endsemesterclock.data.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object{
        val DOMAIN = "http://openAPI.seoul.go.kr:8088/"
        val API_KEY = "784565556d776a733130344b6b677864"
    }
}
interface SeoulOpenService{
    // http://openapi.seoul.go.kr:8088/784565556d776a733130344b6b677864/json/LOCALDATA_072404_NW/1/5/
    @GET("{api_key}/json/LOCALDATA_072404_NW/1/100")
    fun getRestaurant(@Path("api_key") key: String): Call<Restaurant>
}