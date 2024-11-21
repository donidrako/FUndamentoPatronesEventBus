package com.example.fundamentopatroneseventbus

/**
 * Clase sellada que representa los diferentes tipos de eventos deportivos.
 * Las clases selladas permiten tener un número limitado de tipos o subclases.
 * Esto permite un control más estricto y un manejo más seguro de los tipos de eventos posibles.
 */
sealed class SportEvent {

    /**
     * Representa un resultado exitoso de un evento deportivo.
     *
     * @property sportKey Clave única para identificar el deporte relacionado con el evento.
     * @property tipoDeporte Nombre o tipo de deporte en el evento.
     * @property listadoGanadores Lista de nombres de los ganadores del evento deportivo. Puede ser nula si no se proporcionan ganadores.
     * @property isWarning Indicador booleano para señalar si se trata de una advertencia o un resultado normal. El valor predeterminado es `false`.
     */
    data class ResultSuccess(
        val sportKey: Int,                     // Clave única para el deporte.
        val tipoDeporte: String,               // Nombre o tipo de deporte.
        val listadoGanadores: List<String>?,   // Lista opcional de ganadores del evento.
        val isWarning: Boolean = false         // Indicador de advertencia, predeterminado a `false`.
    ) : SportEvent()

    /**
     * Representa un error en un evento deportivo.
     *
     * @property errorKey Clave única para identificar el error.
     * @property typeOfError Tipo de error (por ejemplo, "Conexión fallida", "Datos inválidos").
     */
    data class ResultError(
        val errorKey: Int,                    // Clave única para el error.
        val typeOfError: String               // Tipo de error asociado.
    ) : SportEvent()

    /**
     * Representa un evento de tipo "Anuncio".
     * Este es un objeto único, no tiene propiedades adicionales.
     */
    data object AdEvent : SportEvent()
}
