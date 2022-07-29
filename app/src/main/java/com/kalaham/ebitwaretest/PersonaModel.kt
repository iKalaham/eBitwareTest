package com.kalaham.ebitwaretest

data class PersonaModel(
    val nombre: String ?= "",
    val edad: Int ?= 0,
    val nss: String,
    val sexo: Char ?= MetodosPersona.SEXO_HOMBRE,
    val peso: Double ?=  0.0,
    val altura: Double ?= 0.0
) {
    override fun toString(): String {
        return "PersonaModel(nombre=$nombre, edad=$edad, nss='$nss', sexo=$sexo, peso=$peso, altura=$altura)"
    }
}
