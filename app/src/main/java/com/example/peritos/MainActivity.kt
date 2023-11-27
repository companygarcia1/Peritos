package com.example.peritos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peritos.model.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataSource.VehiculoDataSource().addVehiculo()
    }
}