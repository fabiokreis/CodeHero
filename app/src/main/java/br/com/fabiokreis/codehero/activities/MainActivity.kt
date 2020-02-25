package br.com.fabiokreis.codehero.activities

import android.view.View
import br.com.fabiokreis.codehero.actions.ActionCreator
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.views.character.CharacterLayout

class MainActivity : ReactiveActivity() {
    override fun initialState() {
        syncContent()
    }

    override fun render(): View {
        return CharacterLayout(this)
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState != oldState
    }

    override fun onChanged(state: AppState) {}

    private fun syncContent() {
        ActionCreator.syncCharacters()
    }
}
