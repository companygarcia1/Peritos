package com.example.peritos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peritos.R
import com.example.peritos.databinding.ActivityHomeBinding
import com.example.peritos.model.DataSource
import com.example.peritos.model.accident.Accident
import com.example.peritos.model.vehicle.TipoVehiculo
import com.example.peritos.model.vehicle.Vehicle
import com.example.peritos.ui.NavigationManager
import com.example.peritos.ui.adapters.FragmentAdapter
import com.example.peritos.ui.fragment.AccidentListFragment
import com.example.peritos.ui.fragment.VehicleListFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: FragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configView()
        setListener()
    }

    private fun configView() {
        setSupportActionBar(findViewById(R.id.my_toolbar))

        adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(AccidentListFragment(), "Accidentes")
        adapter.addFragment(VehicleListFragment(), "Vehiculos")

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

    private fun setListener() {

        binding.button.setOnClickListener {

            NavigationManager.openVehicleCreate(this)

        }
    }
}