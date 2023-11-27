package com.example.peritos.model.accident

import android.content.Context

interface IAccidentDataSource {
    fun addAccident(context: Context, accident: Accident)
    fun updateAccident(context: Context, id: Int, accident: Accident)
    fun borrarAccident(context: Context, id: Int)
    fun get(context: Context, id:Int):Accident
    fun listAccident(context:Context):List<Accident>
}