package com.satrasia.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("fetch_data.php?")
    fun getLocation(@Query("id") id: Int): Call<LocationResponse>
}
