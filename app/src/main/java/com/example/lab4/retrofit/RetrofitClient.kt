package com.example.lab4.retrofit

import android.content.Context
import android.widget.Toast
import com.example.lab4.model.WeatherContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(val context: Context) {
    private val request = Retrofit.Builder()
            .baseUrl("http://icomms.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)


    fun getMeteo( updateAdapter : () -> Unit){
        WeatherContent.UPDATE = false

        val myCall = request.getWeather(24)
        myCall.enqueue(object : Callback<List<WeatherContent.WeatherItem>> {
            override fun onFailure(call: Call<List<WeatherContent.WeatherItem>>, t: Throwable) {
                Toast.makeText(context, "Не удалось получить данные", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<WeatherContent.WeatherItem>>,
                response: Response<List<WeatherContent.WeatherItem>>
            ) {
                WeatherContent.ITEMS = response.body()!!.toMutableList()
                WeatherContent.addWeatherData()
                WeatherContent.UPDATE = true
                updateAdapter()
            }
        })
    }
}