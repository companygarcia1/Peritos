package com.example.peritos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peritos.R

class VehicleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_detail)
        configView()
    }
    private fun configView() {
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}