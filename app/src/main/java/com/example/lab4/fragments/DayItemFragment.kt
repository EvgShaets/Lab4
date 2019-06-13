package com.example.lab4.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab4.R

import com.example.lab4.model.WeatherContent
import com.example.lab4.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_dayitem_list.view.*

class DayItemFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var retrofitClient: RetrofitClient
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onRefresh() {
        WeatherContent.UPDATE=false
        swipeRefreshLayout?.list?.adapter = adapterFactory()

        swipeRefreshLayout?.isRefreshing = false
    }

    private fun adapterFactory():DayItemRecyclerViewAdapter{
        return DayItemRecyclerViewAdapter(WeatherContent.ITEM_MAP, listener,retrofitClient)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dayitem_list, container, false)
        swipeRefreshLayout = view as? SwipeRefreshLayout
        swipeRefreshLayout?.setOnRefreshListener ( this )

        if (view is SwipeRefreshLayout) {
            with(view.list) {
                layoutManager = LinearLayoutManager(context)
                retrofitClient = RetrofitClient(context)

                val myAdapter = adapterFactory()

                adapter = myAdapter
            }

        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(position: Int)
    }
}
