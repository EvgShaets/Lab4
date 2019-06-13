package com.example.lab4.model

import java.util.ArrayList
import java.util.HashMap

object WeatherContent {
    var ITEMS: MutableList<WeatherItem> = ArrayList()
    var ITEM_MAP: MutableMap<String, DayWeatherItem> = HashMap()
    var UPDATE = false
    var KEY_ITEM_MAP: ArrayList<String> = ArrayList()

    fun addWeatherData() {
        ITEM_MAP.clear()
        KEY_ITEM_MAP.clear()

        for (item: WeatherItem in ITEMS)
            addDayData(item)
    }

    private fun addDayData(item: WeatherItem) {
        if (ITEM_MAP[item.date] == null) {
            ITEM_MAP[item.date] = createDayWeatherItem()
            KEY_ITEM_MAP.add(item.date)
        }

        ITEM_MAP[item.date]!!.pressure_map[item.tod] = item.pressure
        ITEM_MAP[item.date]!!.temp_map[item.tod] = item.temp
        ITEM_MAP[item.date]!!.feel_map[item.tod] = item.feel
        ITEM_MAP[item.date]!!.humidity_map[item.tod] = item.humidity
        ITEM_MAP[item.date]!!.wind_map[item.tod] = item.wind
        ITEM_MAP[item.date]!!.cloud_map[item.tod] = item.cloud
    }

    private fun createDayWeatherItem(): DayWeatherItem {
        val pressure = HashMap<String, String>(4)
        val temp = HashMap<String, String>(4)
        val feel = HashMap<String, String>(4)
        val humidity = HashMap<String, String>(4)
        val wind = HashMap<String, String>(4)
        val cloud = HashMap<String, String>(4)

        return DayWeatherItem(pressure, temp, feel, humidity, wind, cloud)
    }

    data class WeatherItem(
        val date: String,
        val tod: String,
        val pressure: String,
        val temp: String,
        val feel: String,
        val humidity: String,
        val wind: String,
        val cloud: String
    ) {
        override fun toString(): String {
            return "$date | $tod | $temp"
        }
    }

    data class DayWeatherItem(
        val pressure_map: MutableMap<String, String>,
        val temp_map: MutableMap<String, String>,
        val feel_map: MutableMap<String, String>,
        val humidity_map: MutableMap<String, String>,
        val wind_map: MutableMap<String, String>,
        val cloud_map: MutableMap<String, String>
    )
}