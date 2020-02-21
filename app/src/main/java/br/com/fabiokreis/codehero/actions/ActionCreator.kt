package br.com.fabiokreis.codehero.actions

import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.actions.Actions.SAVE_CHARACTERS
import br.com.fabiokreis.codehero.actions.Actions.SYNC_CHARACTERS
import br.com.fabiokreis.codehero.models.Character
import com.github.raulccabreu.redukt.actions.Action

object ActionCreator {

    fun syncCharacters() {
        asyncDispatch(Action<Any>(SYNC_CHARACTERS))
    }

    fun saveResponseCharacters(response: Map<String, Character>) {
        asyncDispatch(Action(SAVE_CHARACTERS, response))
    }

    private fun asyncDispatch(action: Action<*>) {
        MarvelApplication.redukt.dispatch(action, true)
    }
}