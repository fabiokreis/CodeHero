package br.com.fabiokreis.codehero.reducers

import br.com.fabiokreis.codehero.actions.Actions
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer

class CharactersReducer : BaseAnnotatedReducer<AppState>() {

    @Reduce(Actions.SAVE_CHARACTERS)
    fun saveCharacters(state: AppState, payload: Map<String, Character>): AppState {
        return state.copy(characters = payload)
    }
}