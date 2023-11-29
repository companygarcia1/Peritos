package com.example.peritos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peritos.R
import com.example.peritos.ui.adapters.FragmentAdapter
import com.example.peritos.ui.fragment.AccidentListFragment
import com.example.peritos.ui.fragment.VehicleListFragment
import com.google.android.material.tabs.TabLayoutMediator

class AccidentCrudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accident_crud)
        configView()
    }

    private fun configView() {
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}