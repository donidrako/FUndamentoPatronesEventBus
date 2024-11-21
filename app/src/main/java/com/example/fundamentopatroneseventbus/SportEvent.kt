package com.example.fundamentopatroneseventbus

sealed class SportEvent{
    //clases selladas pueden tener multiples instacias de una sub clase
    //parecidas a las clase ENUM
    data class ResultSuccess(val sportKey: Int,
                             val tipoDeporte: String,
                             val listadoGanadores: List<String>?,
                             val isWarning: Boolean = false ): SportEvent(){
}
data class ResultError(val errorKey: Int,
                       val typeOfError: String ): SportEvent(){

}
    data object AdEvent:SportEvent()
}