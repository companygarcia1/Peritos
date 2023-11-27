package com.example.peritos.model

import com.example.peritos.model.accident.FileAccidentDataSource
import com.example.peritos.model.accident.IAccidentDataSource
import com.example.peritos.model.vehicle.IVehiculoDataSoruce
import com.example.peritos.model.vehicle.VehiculoDataSourceDB
import com.example.peritos.model.vehicle.VehiculoDataSourceFile

object DataSource {

    fun VehiculoDataSource():IVehiculoDataSoruce{
        return VehiculoDataSourceFile("")
    }

    fun accidentDataSource():IAccidentDataSource{
        return FileAccidentDataSource()
    }
}