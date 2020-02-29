package br.com.fabiokreis.codehero.views.character

import android.content.Context
import br.com.fabiokreis.codehero.Anvil.FrameLayoutComponent
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.views.cardLayout
import br.com.fabiokreis.codehero.views.dslAddView

inline fun characterSummaryView(crossinline func: CharacterSummaryView.() -> Unit) {
    dslAddView(func)
}

class CharacterSummaryView(context: Context) : FrameLayoutComponent(context) {
    private var character: Character? = null

    fun character(character: Character) {
        this.character = character
    }

    override fun view() {
        val character = character ?: return

        cardLayout { character(character) }
    }
}

