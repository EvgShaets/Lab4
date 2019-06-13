package com.example.lab4.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab4.R

import com.example.lab4.model.WeatherContent

@SuppressLint("ValidFragment")
class DetailItemFragment(private val position: Int = 0) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailitem_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            //Toast.makeText(context,position.toString(), Toast.LENGTH_SHORT).show()
            with(view) {
                layoutManager = LinearLayoutManager(context)

                adapter = DetailItemRecyclerViewAdapter(WeatherContent.ITEM_MAP, position)
            }
        }
        return view
    }
}
