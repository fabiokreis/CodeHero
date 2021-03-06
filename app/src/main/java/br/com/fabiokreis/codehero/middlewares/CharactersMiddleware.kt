package br.com.fabiokreis.codehero.middlewares

import android.content.Context
import br.com.fabiokreis.codehero.actions.ActionCreator
import br.com.fabiokreis.codehero.actions.Actions
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.services.MarvelServiceApi.getCharacters
import br.com.fabiokreis.codehero.services.MarvelServiceApi.getSearchCharacters
import com.github.raulccabreu.redukt.actions.Action
import com.github.raulccabreu.redukt.middlewares.AfterAction
import com.github.raulccabreu.redukt.middlewares.BeforeAction

class CharactersMiddleware(context: Context) : NetworkOnMiddleware(context) {

    @BeforeAction(Actions.SYNC_CHARACTERS)
    fun syncCharacters(state: AppState, action: Action<*>) {
        val offset = state.characters.size
        val map: MutableMap<String, Character> = mutableMapOf()
        ActionCreator.isLoading(true)
        getCharacters(offset) { characters, _ ->
            if (characters != null)
                map.putAll(characters.data.results.map { it.name to it })
            ActionCreator.saveResponseCharacters(map)
        }
    }

    @AfterAction(Actions.SEARCH_QUERY)
    fun searchCharacter(state: AppState, action: Action<String>) {
        val name = action.payload as String
        val map: MutableMap<String, Character> = mutableMapOf()
        ActionCreator.isLoading(true)
        getSearchCharacters(name) { characters, _ ->
            if (characters != null)
                map.putAll(characters.data.results.map { it.name to it })
            ActionCreator.saveResultCharacters(map)
        }
    }
}
