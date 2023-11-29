package com.example.peritos.model.accident

import android.content.Context
import com.example.peritos.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

class FileAccidentDataSource(private val fileName: String) : IAccidentDataSource {

    private fun saveAccidents(context: Context, accidents: List<Accident>) {
        val file = FileManager.getFile(context, fileName)
        try {
            file.writeText(Gson().toJson(accidents))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadAccidents(context: Context): List<Accident> {
        val file = FileManager.getFile(context, fileName)
        return try {
            val json = file.readText()

            val typeToken = object : TypeToken<List<Accident>>() {}.type
            Gson().fromJson(json, typeToken)


        } catch (e: Exception) {
            mutableListOf()
        }
    }

    override fun addAccident(context: Context, accident: Accident) {
        val accidents = loadAccidents(context).toMutableList()
        accidents.add(accident)
        saveAccidents(context, accidents)
    }

    override fun updateAccident(context: Context, id: Int, accident: Accident) {
        val accidents = loadAccidents(context).toMutableList()
        val index = accidents.indexOfFirst { it.id == id }
        if (index != -1) {
            accidents[index] = accident
            saveAccidents(context, accidents)
        }
    }

    override fun borrarAccident(context: Context, id: Int) {
        val accidents = loadAccidents(context).toMutableList()
        val index = accidents.indexOfFirst { it.id == id }
        if (index != -1) {
            accidents.removeAt(index)
            saveAccidents(context, accidents)
        }
    }

    override fun get(context: Context, id: Int): Accident {
        val accidents = loadAccidents(context)
        return accidents.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Accident not found")
    }

    override fun listAccident(context: Context): List<Accident> {
        return loadAccidents(context)
    }
}