package com.example.peritos.model.vehicle

import android.content.Context

interface IVehiculoDataSoruce {
    fun addVehiculo(context: Context, vehicle: Vehicle)
    fun borrarVehiculo(context: Context,id: Int, vehicle: Vehicle)
    fun actualizarVehiculo(context: Context,id: Int, vehicle: Vehicle)
    fun listarVehiculos(context: Context): List<Vehicle>
    fun vehiculo(context: Context,id: Int):Vehicle?
}