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

    fun filteredCharactersList(state: AppState, offset: Int?): List<Character>? {
        val map: MutableList<Pair<Int, Character>> = mutableListOf()
        val list: MutableList<Character>? = mutableListOf()
        offset?.let {
            state.characters.values.forEachIndexed { index, character ->
                map.addAll(listOf(index to character))
            }

            val filtered = map.filter { it.first == offset || it.first == offset + 1 || it.first == offset + 2
                    || it.first == offset + 3}

            filtered.forEach { list?.add(it.second) }
        }

        return list
    }

}