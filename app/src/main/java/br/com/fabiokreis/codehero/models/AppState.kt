package br.com.fabiokreis.codehero.models

data class AppState(
    val characters: Map<String, Character> = LinkedHashMap(),

    val stateStarted: Boolean = true
)