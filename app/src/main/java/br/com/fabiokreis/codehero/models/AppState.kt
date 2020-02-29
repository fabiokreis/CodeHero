package br.com.fabiokreis.codehero.models

data class AppState(
    val characters: Map<String, Character> = LinkedHashMap(),
    val searchResult: Map<String, Character> = LinkedHashMap(),
    val searchQuery: String? = null,
    val isResult: Boolean = false
) {
    fun filteredCharactersList(offset: Int?): List<Character>? {
        val map: MutableList<Pair<Int, Character>> = mutableListOf()
        val list: MutableList<Character>? = mutableListOf()
        offset?.let {
            getCharactersMap().values.forEachIndexed { index, character ->
                map.addAll(listOf(index to character))
            }

            map.filter {
                it.first == offset
                    || it.first == offset + 1
                    || it.first == offset + 2
                    || it.first == offset + 3
            }
                .forEach { list?.add(it.second) }
        }

        return list
    }

    private fun getCharactersMap() = if (searchResult.isNotEmpty()) searchResult else characters

    fun getTotalCharacters(state: AppState): Int = state.characters.count()
}
