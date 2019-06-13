package com.example.lab4.retrofit

import com.example.lab4.model.WeatherContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("inf/meteo.php?")
    fun getWeather(@Query("tid") tid:Int): Call<List<WeatherContent.WeatherItem>>

}