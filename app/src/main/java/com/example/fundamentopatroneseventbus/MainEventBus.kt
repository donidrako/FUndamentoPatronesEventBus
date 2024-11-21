package com.example.fundamentopatroneseventbus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// Declaración de una variable global para invocar una clase externa llamada EventBus().
private lateinit var eventBus: EventBus
// Declaración de un trabajo (Job) que se asociará con un CoroutineScope.
private val job = Job()
// `Job`: representa una tarea que se puede cancelar o controlar.

// Definición de un ámbito de corutinas utilizando el despachador IO y el trabajo definido.
private val scope = CoroutineScope(Dispatchers.IO + job)
// `CoroutineScope`: define el contexto en el que se ejecutarán las corutinas.
// `Dispatchers.IO`: especifica que las corutinas deben ejecutarse en un hilo optimizado para operaciones de entrada/salida.

fun main() {
    // `fun`: palabra clave para declarar una función.
    // `main`: punto de entrada del programa. Es la primera función que se ejecuta.
    initEventBus() // Llama a una función para inicializar el EventBus.
    runBlocking {
        // `runBlocking`: bloquea el hilo principal y permite ejecutar código que contiene corutinas.
        // configuracionDeSuscripcion(scope) // Configura la primera suscripción al EventBus.
        //sucripcionTwo(scope) // Configura una segunda suscripción al EventBus.
        //configuracionDePublicacion() // Configura la publicación de eventos en el EventBus.
        resultadoExitosoSuscirpsion(scope)
        resultadoConErrorSuscripcion(scope)
        subscribeAnalytics(scope)
        configuracionDePublicacionSportEvent()
    }
}

/**
 * Inicializa el EventBus para manejar la publicación y suscripción de eventos.
 *
 * Esta función asigna una nueva instancia de EventBus a la variable global `eventBus`.
 * Debe llamarse antes de cualquier operación relacionada con el EventBus.
 */
private fun initEventBus() {
    // `private`: hace que esta función solo sea accesible dentro de este archivo o clase.
    // Inicializa el EventBus asignándole una nueva instancia.
    eventBus = EventBus()
}
/**
 * Configura la suscripción para escuchar eventos de tipo `Result`.
 *
 * @param coroutineScope El `CoroutineScope` donde se ejecutará la lógica de suscripción.
 *
 * Este método lanza una corutina que se suscribe al EventBus para recibir eventos de tipo `Result`.
 * Cuando se recibe un evento, imprime el campo `tipoDeporte` en la consola.
 */
suspend fun configuracionDeSuscripcion(coroutineScope: CoroutineScope) {
    // `suspend`: palabra clave para indicar que la función puede ser suspendida.
    // Configura una suscripción al EventBus en un CoroutineScope proporcionado.
    coroutineScope.launch {
        // `launch`: inicia una nueva corutina en el CoroutineScope.
        eventBus.subscribe<Result> { result ->
            // Suscribe un listener al EventBus para recibir eventos de tipo `Result`.
            // Cuando se recibe un evento, se ejecuta la lambda proporcionada.
            println(result.tipoDeporte) // Imprime el valor de `tipoDeporte` en la consola.
        }
    }
}
/**
 * Configura una segunda suscripción para manejar advertencias en eventos de tipo `Result`.
 *
 * @param coroutineScope El `CoroutineScope` donde se ejecutará la lógica de suscripción.
 *
 * Este método lanza una corutina que se suscribe al EventBus para recibir eventos de tipo `Result`.
 * Si el evento contiene una advertencia (`isWarning` es verdadero), imprime un mensaje de advertencia en la consola.
 */
suspend fun sucripcionTwo(coroutineScope: CoroutineScope) {
    // Configura una segunda suscripción con lógica adicional.
    coroutineScope.launch {
        eventBus.subscribe<Result> { result ->
            if (result.isWarning) // Verifica si el evento es una advertencia.
                println("WARNING: ${result.tipoDeporte}") // Imprime un mensaje de advertencia.
        }
    }
}
/**
 * Publica eventos de tipo `Result` en el EventBus.
 *
 * Este método itera sobre los eventos generados por `getEventsInRealtime` y los publica en el EventBus
 * con un retraso de 500 milisegundos entre cada publicación.
 */
suspend fun configuracionDePublicacion() {
    // Configura la publicación de eventos al EventBus.
    getEventsInRealtime().forEach { event ->
        // Itera a través de una lista de eventos proporcionados por `getEventsInRealtime`.
        delay(500) // Suspende la ejecución por 500 milisegundos entre publicaciones.
        eventBus.publish(event) // Publica cada evento en el EventBus.
    }
}
/**
 * Configura una suscripción para manejar eventos exitosos de tipo `SportEvent.ResultSuccess`.
 *
 * @param scope El `CoroutineScope` donde se ejecutará la lógica de suscripción.
 *
 * Este método lanza una corutina que se suscribe al EventBus para recibir eventos exitosos.
 * Cuando se recibe un evento, imprime el código del deporte (`sportKey`) y el tipo de deporte.
 */
fun resultadoExitosoSuscirpsion(scope: CoroutineScope) {

    scope.launch {
        eventBus.subscribe<SportEvent.ResultSuccess> { event ->
            println("Codigo Deporte: ${event.sportKey}, Resultado Correcto: ${event.tipoDeporte}")
        }
    }
}

/**
 * Configura una suscripción para manejar eventos con error de tipo `SportEvent.ResultError`.
 *
 * @param scope El `CoroutineScope` donde se ejecutará la lógica de suscripción.
 *
 * Este método lanza una corutina que se suscribe al EventBus para recibir eventos con error.
 * Cuando se recibe un evento, imprime el código del error (`errorKey`) y el tipo de error.
 */
suspend fun resultadoConErrorSuscripcion(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.ResultError> { event ->
            println("Codigo Error: ${event.errorKey}, Tipo del Error: ${event.typeOfError}")
        }
    }
}

/**
 * Publica eventos de tipo SportEvent en el EventBus.
 *
 * El método utiliza el EventBus para manejar la publicación de eventos,
 * ya que la clase SportEvent es una clase sellada y no tiene un método publish.
 * Asegúrate de usar eventBus, que es responsable de gestionar la publicación
 * y suscripción de eventos en lugar de intentar usar sportEvent directamente.
 */
suspend fun configuracionDePublicacionSportEvent() {
    scope.launch { getAdEventsInRealtime().forEach { adEvent ->
        delay(someTime()*2)
        eventBus.publish(adEvent)
    } }
    getResultEventsInRealtime().forEach { resultEvent ->
        delay(someTime()) // Suspende la ejecución por un tiempo aleatorio.
        eventBus.publish(resultEvent) // Publica cada evento en el EventBus.
    }

}
/**
 * Genera un tiempo de retraso aleatorio entre 500 y 2000 milisegundos.
 *
 * @return Un valor aleatorio de tipo `Long` representando el tiempo de retraso.
 */
fun someTime(): Long = Random.nextLong(500, 2_000)

suspend fun subscribeAnalytics(scope: CoroutineScope) {
    com.example.fundamentopatroneseventbus.scope.launch {
    eventBus.subscribe<SportEvent.AdEvent> {
        event ->
        println("Ad click. Send data server...")
    }
    }
}
