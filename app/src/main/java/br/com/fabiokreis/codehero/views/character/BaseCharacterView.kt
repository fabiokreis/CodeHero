package br.com.fabiokreis.codehero.views.character

import android.content.Context
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.views.ReactRenderableView
import trikita.anvil.Anvil

abstract class BaseCharacterView(context: Context): ReactRenderableView(context) {

    protected var character: Character? = null

    fun character(character: Character) {
        this.character = character
        Anvil.render()
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState.characters != oldState.characters
    }

    override fun onChanged(state: AppState) {
        Anvil.render(this)
    }
}