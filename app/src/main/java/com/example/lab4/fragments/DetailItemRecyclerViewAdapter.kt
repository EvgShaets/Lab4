package com.example.lab4.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lab4.R

import com.example.lab4.model.WeatherContent

import kotlinx.android.synthetic.main.fragment_detailitem.view.*

class DetailItemRecyclerViewAdapter(
    private val mValues: MutableMap<String, WeatherContent.DayWeatherItem>,
    private val positionDay: Int
) : RecyclerView.Adapter<DetailItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detailitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = WeatherContent.KEY_ITEM_MAP[positionDay]
        when (position) {
            0 -> holder.todView.text = "Ночь"
            1 -> holder.todView.text = "Утро"
            2 -> holder.todView.text = "День"
            3 -> holder.todView.text = "Вечер"
        }

        holder.tempView.text = mValues[key]!!.temp_map[position.toString()]
        holder.feelView.text = mValues[key]!!.feel_map[position.toString()]
        holder.cloudView.text = mValues[key]!!.cloud_map[position.toString()]
        holder.humidityView.text = mValues[key]!!.humidity_map[position.toString()]
        holder.pressureView.text = mValues[key]!!.pressure_map[position.toString()]
        holder.windView.text = mValues[key]!!.wind_map[position.toString()]
    }

    override fun getItemCount(): Int = 4

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val todView: TextView = mView.tod
        val tempView: TextView = mView.temp
        val feelView: TextView = mView.feel
        val cloudView: TextView = mView.cloud
        val humidityView: TextView = mView.humidity
        val pressureView: TextView = mView.pressure
        val windView: TextView = mView.wind
    }
}
