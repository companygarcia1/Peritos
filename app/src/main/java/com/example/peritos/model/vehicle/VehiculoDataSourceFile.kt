package com.example.peritos.model.vehicle

import java.io.File

class VehiculoDataSourceFile(private val filePath: String) : IVehiculoDataSoruce {

    private val vehicles: MutableList<Vehicle> = mutableListOf()

    init {
        // Cargar los vehículos existentes del archivo al iniciar
        cargarDatos()
    }

    override fun addVehiculo(vehicle: Vehicle) {
        vehicles.add(vehicle)
        guardarDatos()
    }

    override fun borrarVehiculo(id: Int, vehicle: Vehicle) {
        vehicles.removeIf { it.id == id && it == vehicle }
        guardarDatos()
    }

    override fun actualizarVehiculo(id: Int, vehicle: Vehicle) {
        val index = vehicles.indexOfFirst { it.id == id }
        if (index != -1) {
            vehicles[index] = vehicle
            guardarDatos()
        }
    }

    override fun listarVehiculos(): List<Vehicle> {
        return vehicles.toList()
    }

    override fun vehiculo(id: Int) {
        val result = vehicles.find { it.id == id }
        // Aquí puedes hacer algo con el resultado, como imprimirlo
        println(result)
    }

    private fun cargarDatos() {
        if (File(filePath).exists()) {
            vehicles.addAll(File(filePath).readLines().map { deserializeVehicle(it) })
        }
    }

    private fun guardarDatos() {
        File(filePath).writeText(vehicles.joinToString("\n") { serializeVehicle(it) })
    }

    private fun serializeVehicle(vehicle: Vehicle): String {
        return "${vehicle.id},${vehicle.seguro},${vehicle.matricula},${vehicle.tipoVehiculo.name}"
    }

    private fun deserializeVehicle(line: String): Vehicle {
        val parts = line.split(",")
        return Vehicle(parts[0].toInt(), parts[1], parts[2], TipoVehiculo.valueOf(parts[3]))
    }
}