package br.com.fabiokreis.codehero.models

data class AppState(
    val characters: Map<String, Character> = LinkedHashMap(),

    val stateStarted: Boolean = true
) {
    fun search(state: AppState, name: String? = null): List<Character>? =
        name?.let {
            state.characters.filter { it.key.toLowerCase().contains(name.toLowerCase().toRegex()) }
        }
            ?.values?.toList()
}