package com.example.fundamentopatroneseventbus

data class Result(val sportKey: Int, val tipoDeporte: String,   val listadoGanadores: List<String>?,
             val isWarning: Boolean = false ) {

}