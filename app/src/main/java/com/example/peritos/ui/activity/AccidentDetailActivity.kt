package com.example.peritos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peritos.R

class AccidentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accident_detail)
        configView()
    }

    companion object {
        val EXTRA_ID: String="ID"
    }


    private fun configView() {
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}