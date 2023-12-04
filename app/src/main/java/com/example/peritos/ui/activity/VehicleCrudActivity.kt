package com.example.peritos.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.peritos.R
import com.example.peritos.databinding.ActivityVehicleCrudBinding
import com.example.peritos.model.DataSource
import com.example.peritos.model.vehicle.TipoVehiculo
import com.example.peritos.model.vehicle.Vehicle
import com.example.peritos.utils.DialogManager


class VehicleCrudActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVehicleCrudBinding
    private var id: Int = 0
    private var vehicle: Vehicle? = null

    companion object {
        val EXTRA_ID: String = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleCrudBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configView()
        setListener()
    }

    private fun configView() {
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            TipoVehiculo.values().map { it.name }
        )

        binding.spinner.setAdapter(adapter)

        id = intent.getIntExtra(VehicleDetailActivity.EXTRA_ID, 0)
        if (id > 0) {
            vehicle = DataSource.vehicleDataSource().vehiculo(this, id)
            binding.editMatricula.editText?.setText(vehicle?.matricula)
            binding.editSerguro.editText?.setText(vehicle?.seguro)
            binding.editType.editText?.setText(vehicle?.tipoVehiculo?.name)
        }

    }

    private fun setListener() {
        binding.button.setOnClickListener {
            val matricula = binding.editMatricula.editText?.text.toString()
            val seguro = binding.editSerguro.editText?.text.toString()
            val tipoVehiculo = binding.editType.editText?.text.toString()
            if (vehicle == null) {
                saveCard(matricula, seguro, tipoVehiculo)
            } else {
                updateCard(id, matricula, seguro, tipoVehiculo)
            }
        }

        binding.myToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun saveCard(matricula: String, seguro: String, tipoVehiculo: String) {
        if (controlCard(matricula,seguro,tipoVehiculo)) {
            DataSource.vehicleDataSource()
                .addVehiculo(
                    this,
                    Vehicle(0, seguro, matricula, TipoVehiculo.valueOf(tipoVehiculo))
                )
            finish()
        }
    }

    private fun updateCard(id: Int, matricula: String, seguro: String, tipoVehiculo: String) {
        if (controlCard(matricula,seguro,tipoVehiculo)){
            DataSource.vehicleDataSource()
                .actualizarVehiculo(
                    this,
                    id,
                    Vehicle(0, seguro, matricula, TipoVehiculo.valueOf(tipoVehiculo))
                )
            finish()
        }

    }

    private fun controlCard(matricula: String, seguro: String, tipoVehiculo: String): Boolean {
        var accepted = true
        if (matricula.isEmpty()) {
            DialogManager.alertDialog(
                this,
                "Faltan datos",
                "El campo matricula no puede estar vacio."
            )
            accepted = false
        }
        if (seguro.isEmpty()) {
            DialogManager.alertDialog(this, "Faltan datos", "El campo seguro no puede estar vacio.")
            accepted = false
        }
        if (tipoVehiculo.isEmpty()) {
            DialogManager.alertDialog(
                this,
                "Faltan datos",
                "El campo tipo vehiculo no puede estar vacio."
            )
            accepted = false
        }
        return accepted
    }

}