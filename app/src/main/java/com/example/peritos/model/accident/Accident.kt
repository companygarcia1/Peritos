package com.example.peritos.model.accident

import com.example.peritos.model.vehicle.Vehicle
import java.util.Date

data class Accident(
    var id: Int,
    var lugar: String,
    var fecha: Date,
    var vehiculo: Vehicle,
    var foto: String,
    var comentario: String,
    var tlf: String,
    var tipoDaño: TipoDaño
)
