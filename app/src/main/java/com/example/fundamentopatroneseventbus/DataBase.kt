package com.example.fundamentopatroneseventbus

/**
 * Devuelve una lista de resultados de eventos deportivos en tiempo real.
 * Cada evento está representado por un objeto `Result`, que contiene información sobre el deporte, los ganadores y si es una advertencia.
 *
 * @return Una lista de objetos `Result` con eventos deportivos, donde cada evento contiene:
 *  - `sportKey`: Clave única para el deporte.
 *  - `tipoDeporte`: Nombre o tipo de deporte.
 *  - `listadoGanadores`: Lista de ganadores del evento (puede ser nula).
 *  - `isWarning`: Indicador booleano si es una advertencia (predeterminado a `false`).
 */
fun getEventsInRealtime() = listOf(
    Result(1, "Fútbol", listOf("Italia", "Perú", "Corea del Sur")),
    Result(2, "Levantamiento de Pesas", listOf("Mongolia", "Alemania", "Turquía")),
    Result(3, "Gimnasia Rítmica", listOf("Rusia", "USA", "Francia")),
    Result(4, "Polo Acuático", listOf("España", "Vietnam", "USA")),
    Result(5, "Béisbol", null, true),
    Result(6, "Rugby", listOf("Sudáfrica", "Qatar", "Rumanía")),
    Result(7, "Tenis", listOf("España", "México", "Colombia"))
)

/**
 * Devuelve una lista de eventos deportivos con el estado de los resultados en tiempo real.
 * Los eventos pueden ser exitosos o contener errores.
 *
 * @return Una lista de objetos `SportEvent`, que incluye:
 *  - `ResultSuccess`: Evento exitoso con los resultados deportivos.
 *  - `ResultError`: Evento que contiene un error (por ejemplo, error de red o permisos).
 */
fun getResultEventsInRealtime() = listOf(
    SportEvent.ResultSuccess(1, "Fútbol", listOf("Italia", "Perú", "Corea del Sur")),
    SportEvent.ResultSuccess(2, "Levantamiento de Pesas", listOf("Mongolia", "Alemania", "Turquía")),
    SportEvent.ResultError(10, "Error de red."),
    SportEvent.ResultSuccess(3, "Gimnasia Rítmica", listOf("Rusia", "USA", "Francia")),
    SportEvent.ResultSuccess(4, "Polo Acuático", listOf("España", "Vietnam", "USA")),
    SportEvent.ResultSuccess(5, "Béisbol", null, true),
    SportEvent.ResultError(20, "Error de permisos."),
    SportEvent.ResultSuccess(6, "Rugby", listOf("Sudáfrica", "Qatar", "Rumanía")),
    SportEvent.ResultSuccess(7, "Tenis", listOf("España", "México", "Colombia"))
)

/**
 * Devuelve una lista de eventos de tipo "Anuncio" en tiempo real.
 * Estos eventos no contienen información adicional más allá de marcar el tipo de evento.
 *
 * @return Una lista de objetos `SportEvent.AdEvent`, que son eventos de anuncio sin propiedades adicionales.
 */
fun getAdEventsInRealtime() = listOf(
    SportEvent.AdEvent,
    SportEvent.AdEvent
)
