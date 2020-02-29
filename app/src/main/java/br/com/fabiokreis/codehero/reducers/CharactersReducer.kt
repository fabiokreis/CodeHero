package br.com.fabiokreis.codehero.reducers

import br.com.fabiokreis.codehero.actions.Actions
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer

class CharactersReducer : BaseAnnotatedReducer<AppState>() {

    @Reduce(Actions.SAVE_CHARACTERS)
    fun saveCharacters(state: AppState, payload: Map<String, Character>): AppState {
        return state.copy(characters = payload, isResult = false)
    }

    @Reduce(Actions.SEARCH_QUERY)
    fun saveSearchQuery(state: AppState, payload: String): AppState {
        return state.copy(searchQuery = payload)
    }

    @Reduce(Actions.CLEAR_SEARCH)
    fun clearSearch(state: AppState, payload: Any?): AppState {
        return state.copy(searchQuery = null, searchResult = linkedMapOf(), isResult = false)
    }

    @Reduce(Actions.SEARCH_RESULT)
    fun saveResultCharacters(state: AppState, payload: Map<String, Character>): AppState {
        return state.copy(searchResult = payload, isResult = true)
    }
}
