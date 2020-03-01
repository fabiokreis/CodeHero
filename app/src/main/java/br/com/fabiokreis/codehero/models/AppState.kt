package br.com.fabiokreis.codehero.models

import kotlin.math.min

data class AppState(
    val characters: Map<String, Character> = LinkedHashMap(),
    val searchResult: Map<String, Character> = LinkedHashMap(),
    val isLoading: Boolean = false,
    val offset: Int = 0,
    val itemsPerPage: Int = 4,
    val searchQuery: String? = null,
    val isResult: Boolean = false
) {
    fun filteredCharactersList(): List<Character>? {
        return if (getTotalCharacters() > 0 && getTotalCharacters() >= offset) {
            getCharactersMap().values.toList().subList(offset,
                min(offset + itemsPerPage, getTotalCharacters()))
        } else if (getTotalCharacters() < itemsPerPage) {
            getCharactersMap().values.toList()
        } else {
            null
        }
    }

    private fun getCharactersMap() =
        if (searchQuery != null && searchResult.isNotEmpty()) searchResult else characters

    fun getNumberOfVisibleButtons(actual: Int): Int {
        val total: Int = getTotalCharacters() - (actual * itemsPerPage)
        return min(getTotalOfButtons(total), 2)
    }

    fun getTotalOfButtons(total: Int = getTotalCharacters()): Int {
        return if ((total % itemsPerPage) == 0) total / itemsPerPage else (total / itemsPerPage) + 1
    }

    fun getTotalCharacters(): Int = getCharactersMap().count()
}
