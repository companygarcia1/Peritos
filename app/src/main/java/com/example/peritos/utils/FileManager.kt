package com.example.peritos.utils

import android.content.Context
import java.io.File

object FileManager {

     fun getFile(context: Context, filePath: String): File {
        val carsFile = File(context.filesDir,filePath)
        if (!carsFile.exists()) {
            try {
                carsFile.createNewFile()
                println("El archivo se ha creado correctamente.")
            } catch (e: Exception) {
                println("Error al crear el archivo: ${e.message}")
            }
        } else {
            println("El archivo ya existe.")
        }
        return carsFile
    }
}