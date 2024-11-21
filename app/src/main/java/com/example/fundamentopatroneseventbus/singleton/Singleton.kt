package com.example.fundamentopatroneseventbus.singleton

import com.example.fundamentopatroneseventbus.eventBus.EventBus

/**
 * Singleton que gestiona la instancia única de EventBus.
 * Utiliza el patrón Singleton para garantizar que solo exista una instancia de EventBus
 * en toda la aplicación. La instancia se crea de forma perezosa (lazy) cuando se necesita.
 */
object Singleton {

    // Instancia única de EventBus, se inicializa solo cuando se accede a ella por primera vez.
    private val eventBusInstance: EventBus by lazy { EventBus() }

    /**
     * Obtiene la instancia del EventBus.
     *
     * @return La instancia única de EventBus.
     */
    fun eventBusInstance() = eventBusInstance
}
