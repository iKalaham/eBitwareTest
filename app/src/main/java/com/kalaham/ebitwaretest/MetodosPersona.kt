package com.kalaham.ebitwaretest

import java.util.*

object MetodosPersona {

    const val PESO_MAYOR: Int = 1
    const val PESO_IDEAL: Int = 0
    const val PESO_MENOR: Int = -1

    const val SEXO_HOMBRE: Char = 'H'
    const val SEXO_MUJER: Char = 'M'

    fun calcularImc(peso: Double, altura: Double, sexo: Char): Int {

        val alturaSquare = altura * altura
        val result: Double = peso / alturaSquare

        if (sexo == SEXO_HOMBRE){
            if (result < 20.0) return PESO_MENOR
            if (result >= 20.0 && result <= 25.0) return PESO_IDEAL
            if (result > 25.0) return PESO_MAYOR
        } else if (sexo == SEXO_MUJER){ // Mujer
            if (result < 19.0) return PESO_MENOR
            if (result >= 19.0 && result <= 24.0) return PESO_IDEAL
            if (result > 24.0) return PESO_MAYOR
        }
        return PESO_MENOR

    }

    fun generaNSS(): String {
        val random: String = UUID.randomUUID().toString()
        return random.substring(0,8)
    }

    fun esMayorDeEdad(edad: Int): Boolean {
        return edad >= 18
    }

    fun comprobarSexo(sexo: Char, isFemale: Boolean): Boolean {
        if (isFemale) {
            if (sexo == SEXO_MUJER) return true else return false
        } else {
            if (sexo == SEXO_HOMBRE) return false else return true
        }
    }

}