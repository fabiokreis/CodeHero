package br.com.fabiokreis.codehero.views.character

import android.content.Context
import br.com.fabiokreis.codehero.views.CardLayout.cardLayout
import br.com.fabiokreis.codehero.views.dslAddView

inline fun characterSummaryView(crossinline func: CharacterSummaryView.() -> Unit) {
    dslAddView(func)
}

class CharacterSummaryView(context: Context) : BaseCharacterView(context) {
    override fun view() {
        val character = character ?: return

        cardLayout(context, character)
    }
}

