package com.example.lab4

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lab4.fragments.DayItemFragment
import com.example.lab4.fragments.DetailItemFragment

class MainActivity : AppCompatActivity(), DayItemFragment.OnListFragmentInteractionListener{
    override fun onListFragmentInteraction(position: Int) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val detailFragment = DetailItemFragment(position)
            supportFragmentManager.beginTransaction()
                .replace(R.id.listDetail, detailFragment)
                .commit()
        } else {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = DayItemFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.listDay, fragment)
            .commit()
    }
}
