package com.example.peritos.model.vehicle

import android.content.Context
import com.example.peritos.utils.Commons
import com.example.peritos.utils.FileManager
import java.io.File

class VehiculoDataSourceFile(private val filePath: String) : IVehiculoDataSoruce {

    private val vehicles: MutableList<Vehicle> = mutableListOf()

    override fun addVehiculo(context: Context, vehicle: Vehicle) {
        cargarDatos(context)
        vehicle.id = Commons.generarNumeroAleatorio(0, 10000)
        vehicles.add(vehicle)
        guardarDatos(context)
    }

    override fun borrarVehiculo(context: Context, id: Int, vehicle: Vehicle) {
        cargarDatos(context)
        vehicles.removeIf { it.id == id && it == vehicle }
        guardarDatos(context)
    }

    override fun actualizarVehiculo(context: Context, id: Int, vehicle: Vehicle) {
        cargarDatos(context)
        val index = vehicles.indexOfFirst { it.id == id }
        if (index != -1) {
            vehicles[index] = vehicle
            guardarDatos(context)
        }
    }

    override fun listarVehiculos(context: Context): List<Vehicle> {
        cargarDatos(context)
        return vehicles.toList()
    }

    override fun vehiculo(context: Context, id: Int): Vehicle? {
        cargarDatos(context)
        return vehicles.find { it.id == id }
    }

    private fun cargarDatos(context: Context) {
        if (FileManager.getFile(context, filePath).exists()) {
            vehicles.addAll(
                FileManager.getFile(context, filePath).readLines().map { deserializeVehicle(it) })
        }
    }

    private fun guardarDatos(context: Context) {
        FileManager.getFile(context, filePath)
            .writeText(vehicles.joinToString("\n") { serializeVehicle(it) })
    }

    private fun serializeVehicle(vehicle: Vehicle): String {
        return "${vehicle.id},${vehicle.seguro},${vehicle.matricula},${vehicle.tipoVehiculo.name}"
    }

    private fun deserializeVehicle(line: String): Vehicle {
        val parts = line.split(",")
        return Vehicle(parts[0].toInt(), parts[1], parts[2], TipoVehiculo.valueOf(parts[3]))
    }

}