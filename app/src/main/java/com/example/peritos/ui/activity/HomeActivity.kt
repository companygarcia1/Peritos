package com.example.peritos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
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
    private var accidentListFragment: AccidentListFragment? = null

    private var activeFragment = 0
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
        accidentListFragment = AccidentListFragment()
        adapter.addFragment(accidentListFragment!!, "Accidentes")
        adapter.addFragment(VehicleListFragment(), "Vehiculos")

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_all -> {
                accidentListFragment?.filterAll()
                return true
            }
            R.id.filter_grave -> {
                accidentListFragment?.filterGrave()
                return true
            }

            R.id.filter_medium -> {
                accidentListFragment?.filterMedium()
                return true
            }

            R.id.filter_light -> {
                accidentListFragment?.filterLight()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListener() {

        binding.button.setOnClickListener {
            when (activeFragment) {
                0 -> {
                    NavigationManager.openAccidentCreate(this)
                }

                1 -> {
                    NavigationManager.openVehicleCreate(this)
                }
            }
        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                activeFragment = position
            }
        })
    }
}