package com.example.fundamentopatroneseventbus.eventBus

import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlin.coroutines.coroutineContext

/**
 * EventBus: una implementación basada en Kotlin Flow del patrón de diseño Event Bus.
 *
 * Esta clase facilita la comunicación entre componentes desacoplados mediante la publicación
 * y suscripción de eventos genéricos.
 *
 * Características:
 * - Publicación de eventos de cualquier tipo.
 * - Suscripción específica a eventos basados en su tipo.
 */
class EventBus {

    // Un flujo compartido (SharedFlow) que mantiene los eventos publicados.
    // MutableSharedFlow permite emitir valores dinámicamente.
    private val _events = MutableSharedFlow<Any>()

    /**
     * Exposición del flujo de eventos como SharedFlow,
     * para que sea accesible de manera segura fuera de esta clase.
     */
    val events: SharedFlow<Any> = _events

    /**
     * Publica un evento en el flujo compartido.
     *
     * @param event Evento genérico que será emitido al flujo.
     *
     * Ejemplo de uso:
     * ```
     * eventBus.publish(MyEvent("Ejemplo de evento"))
     * ```
     */
    suspend fun publish(event: Any) {
        _events.emit(event)
    }

    /**
     * Suscribe un consumidor a eventos de un tipo específico.
     *
     * Esta función utiliza un filtro por tipo (`filterIsInstance`) para garantizar
     * que solo se procesen eventos del tipo especificado.
     *
     * @param T Tipo del evento al que se desea suscribir.
     * @param onEvent Función lambda que define la lógica a ejecutar cuando ocurre un evento de tipo T.
     *
     * Ejemplo de uso:
     * ```
     * eventBus.subscribe<MyEvent> { event ->
     *     println("Evento recibido: ${event.message}")
     * }
     * ```
     */
    suspend inline fun <reified T> subscribe(crossinline onEvent: (T) -> Unit) {
        // Filtra el flujo para recibir solo eventos del tipo especificado.
        events.filterIsInstance<T>()
            .collectLatest { event ->
                // Verifica que el contexto de la coroutine esté activo antes de ejecutar la lógica.
                coroutineContext.ensureActive()
                // Ejecuta la lógica proporcionada para el evento.
                onEvent(event)
            }
    }
}
