package com.example.lab4

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lab4.fragments.DetailItemFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        setContentView(R.layout.activity_detail)

        val position = intent.getIntExtra("position",0)
        val fragment = DetailItemFragment(position)

        supportFragmentManager.beginTransaction()
            .replace(R.id.detailList,fragment)
            .commit()
    }
}
