package com.example.peritos.model.vehicle

interface IVehiculoDataSoruce {
    fun addVehiculo(vehicle: Vehicle)
    fun borrarVehiculo(id: Int, vehicle: Vehicle)
    fun actualizarVehiculo(id: Int, vehicle: Vehicle)
    fun listarVehiculos(): List<Vehicle>
    fun vehiculo(id: Int)
}