package com.example.peritos.utils

import kotlin.random.Random

object Commons {
    fun generarNumeroAleatorio(minimo: Int, maximo: Int): Int {
        // Verifica que el rango sea válido
        require(minimo < maximo) { "El valor mínimo debe ser menor que el valor máximo." }

        // Genera un número aleatorio en el rango [minimo, maximo)
        return Random.nextInt(minimo, maximo)
    }
}