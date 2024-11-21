package com.example.fundamentopatroneseventbus

/**
 * Clase de datos que representa el resultado de un evento deportivo.
 *
 * @property sportKey Clave única para identificar el deporte.
 * @property tipoDeporte Nombre o tipo de deporte relacionado con el resultado.
 * @property listadoGanadores Lista de nombres de los ganadores del evento deportivo. Puede ser nula si no se proporcionan ganadores.
 * @property isWarning Indicador booleano para señalar si se trata de una advertencia o un resultado normal. El valor predeterminado es `false`.
 */
data class Result(
    val sportKey: Int,                     // Clave única para el deporte.
    val tipoDeporte: String,               // Nombre o tipo de deporte.
    val listadoGanadores: List<String>?,   // Lista opcional de ganadores del evento.
    val isWarning: Boolean = false        // Indicador de advertencia, predeterminado a `false`.
)
