package br.com.fabiokreis.codehero.actions

import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.actions.Actions.CLEAR_SEARCH
import br.com.fabiokreis.codehero.actions.Actions.SAVE_CHARACTERS
import br.com.fabiokreis.codehero.actions.Actions.SEARCH_QUERY
import br.com.fabiokreis.codehero.actions.Actions.SEARCH_RESULT
import br.com.fabiokreis.codehero.actions.Actions.SYNC_CHARACTERS
import br.com.fabiokreis.codehero.models.Character
import com.github.raulccabreu.redukt.actions.Action

object ActionCreator {

    fun syncCharacters(offset: Int) {
        asyncDispatch(Action(SYNC_CHARACTERS, offset))
    }

    fun saveResponseCharacters(response: Map<String, Character>) {
        asyncDispatch(Action(SAVE_CHARACTERS, response))
    }

    fun searchQuery(name: String) {
        asyncDispatch(Action(SEARCH_QUERY, name))
    }

    fun clearSearch() {
        asyncDispatch(Action<Void>(CLEAR_SEARCH))
    }

    fun saveResultCharacters(response: Map<String, Character>) {
        asyncDispatch(Action(SEARCH_RESULT, response))
    }

    private fun asyncDispatch(action: Action<*>) {
        MarvelApplication.redukt.dispatch(action, true)
    }
}