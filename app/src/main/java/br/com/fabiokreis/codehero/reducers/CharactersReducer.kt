package br.com.fabiokreis.codehero.reducers

import br.com.fabiokreis.codehero.actions.Actions
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer

class CharactersReducer : BaseAnnotatedReducer<AppState>() {

    @Reduce(Actions.SAVE_CHARACTERS)
    fun saveCharacters(state: AppState, payload: Map<String, Character>): AppState {
        return state.copy(characters = state.characters.plus(payload), isResult = false, isLoading = false)
    }

    @Reduce(Actions.UPDATE_OFFSET)
    fun updateOffset(state: AppState, payload: Int): AppState {
        return state.copy(offset = payload)
    }

    @Reduce(Actions.SEARCH_QUERY)
    fun saveSearchQuery(state: AppState, payload: String): AppState {
        return state.copy(searchQuery = payload, offset = 0)
    }

    @Reduce(Actions.CLEAR_SEARCH)
    fun clearSearch(state: AppState, payload: Any?): AppState {
        return state.copy(searchQuery = null, searchResult = linkedMapOf(), isResult = false, offset = 0)
    }

    @Reduce(Actions.SEARCH_RESULT)
    fun saveResultCharacters(state: AppState, payload: Map<String, Character>): AppState {
        return state.copy(searchResult = payload, isResult = true, isLoading = false)
    }

    @Reduce(Actions.IS_LOADING)
    fun isLoading(state: AppState, payload: Boolean): AppState {
        return state.copy(isLoading = payload)
    }
}
