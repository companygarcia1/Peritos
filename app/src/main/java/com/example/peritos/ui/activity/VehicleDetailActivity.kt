package com.example.peritos.ui.activity

import android.R.menu
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.peritos.R
import com.example.peritos.databinding.ActivityVehicleDetailBinding
import com.example.peritos.model.DataSource
import com.example.peritos.ui.NavigationManager


class VehicleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleDetailBinding
    private var id: Int = 0

    companion object {
        val EXTRA_ID: String = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configView()
        setListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.vehicle_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                NavigationManager.openVehicleUpdate(this, id)
                return true
            }

            R.id.action_delete -> {
                DataSource.vehicleDataSource().borrarVehiculo(this, id)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configView() {
        setSupportActionBar(findViewById(R.id.my_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        id = intent.getIntExtra(EXTRA_ID, 0)
        val vehicule = DataSource.vehicleDataSource().vehiculo(this, id)
        binding.editMatricula.editText?.setText(vehicule?.matricula)
        binding.editSerguro.editText?.setText(vehicule?.seguro)
        binding.editType.editText?.setText(vehicule?.tipoVehiculo?.name)

        binding.editMatricula.editText?.isEnabled = false
        binding.editSerguro.editText?.isEnabled = false
        binding.editType.editText?.isEnabled = false

    }

    private fun setListener(){
        binding.myToolbar.setNavigationOnClickListener {
            finish()
        }
    }

}