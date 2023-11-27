package com.example.peritos.model.vehicle

import android.content.Context

class VehiculoDataSourceDB (context: Context) : IVehiculoDataSoruce {


    override fun addVehiculo(vehicle: Vehicle) {

    }

    override fun borrarVehiculo(id: Int, vehicle: Vehicle) {

    }

    override fun actualizarVehiculo(id: Int, vehicle: Vehicle) {

    }

    override fun listarVehiculos(): List<Vehicle> {
        return dbHelper.getVehiculos()
    }

    override fun vehiculo(id: Int) {

    }
}