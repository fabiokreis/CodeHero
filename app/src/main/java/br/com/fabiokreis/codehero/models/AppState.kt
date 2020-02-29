package br.com.fabiokreis.codehero.models

import kotlin.math.min

data class AppState(
    val characters: Map<String, Character> = LinkedHashMap(),
    val searchResult: Map<String, Character> = LinkedHashMap(),
    val offset: Int = 0,
    val searchQuery: String? = null,
    val isResult: Boolean = false
) {
    fun filteredCharactersList(): List<Character>? {
        val map: MutableList<Pair<Int, Character>> = mutableListOf()
        val list: MutableList<Character>? = mutableListOf()
        offset.let {
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

    private fun getCharactersMap() = if (searchQuery != null) searchResult else characters

    fun getNumberOfVisibleButtons(actual: Int): Int {
        val total: Int = getTotalCharacters() - (actual * 4)
        return min(if ((total % 4) == 0) total / 4 else (total / 4) + 1, 2)
    }

    fun getTotalOfButtons(): Int {
        val total = getTotalCharacters()
        return if ((total % 4) == 0) total / 4 else (total / 4) + 1
    }

    fun getTotalCharacters(): Int = getCharactersMap().count()
}
