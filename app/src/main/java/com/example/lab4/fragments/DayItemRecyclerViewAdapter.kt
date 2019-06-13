package com.example.lab4.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lab4.R

import com.example.lab4.model.WeatherContent
import com.example.lab4.retrofit.RetrofitClient

import kotlinx.android.synthetic.main.fragment_dayitem.view.*

class DayItemRecyclerViewAdapter(
    private val mValues: MutableMap<String, WeatherContent.DayWeatherItem>,
    private val mListener: DayItemFragment.OnListFragmentInteractionListener?,
    private val retrofitClient: RetrofitClient
) : RecyclerView.Adapter<DayItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        if(!WeatherContent.UPDATE){
            retrofitClient.getMeteo {notifyDataSetChanged()}
        }

        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Int
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_dayitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = WeatherContent.KEY_ITEM_MAP[position]
        holder.mIdView.text = WeatherContent.KEY_ITEM_MAP[position]
        holder.mContentView.text = mValues[key]!!.temp_map["2"]

        with(holder.mView) {
            tag = position
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.count()

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
