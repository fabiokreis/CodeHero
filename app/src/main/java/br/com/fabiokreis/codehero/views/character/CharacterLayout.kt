package br.com.fabiokreis.codehero.views.character

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import br.com.fabiokreis.codehero.R
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.views.BottomMenu.bottomMenu
import br.com.fabiokreis.codehero.views.ReactRenderableView
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.*

class CharacterLayout(context: Context): ReactRenderableView(context) {

    private var characters = listOf<Character>(
        Character(name = "nome 1"),
        Character(name = "nome 2"),
        Character(name = "nome 3"),
        Character(name = "nome 4"),
        Character(name = "nome 5")

    )
    private var textSearch: String = ""

    private val redMarvel = Color.parseColor("#D42026")

    override fun view() {
        relativeLayout {
            size(MATCH, MATCH)
            backgroundColor(Color.WHITE)
            renderTitle()
            renderSearch()
            renderSummaryTitle()
            renderSummary()
            renderButtonButtons()
        }
    }

    private fun renderTitle() {
        linearLayout {
            id(10)
            alignParentTop()
            size(MATCH, WRAP)
            orientation(HORIZONTAL)
            margin(dip(24), dip(12), 0, dip(12))

            textView {
                text(R.string.busca)
                textColor(redMarvel)
                textSize(sip(24f))
                typeface(null, Typeface.BOLD)
            }

            textView {
                text(R.string.teste)
                textColor(redMarvel)
                textSize(sip(24f))
                typeface(null, Typeface.NORMAL)
            }
        }
    }

    private fun renderSearch() {
        linearLayout {
            id(11)
            below(10)
            size(MATCH, WRAP)
            orientation(VERTICAL)
            margin(dip(24), 0, dip(24), 0)

            textView {
                text(R.string.nome_personagem)
                textColor(redMarvel)
                textSize(sip(24f))
                typeface(null, Typeface.BOLD)
            }

            editText {
                backgroundResource(R.drawable.rounded_edit_text)
                text(textSearch)
                textColor(redMarvel)
                onTextChanged {
                    textSearch = it.toString()
                }
            }
        }
    }

    private fun renderSummaryTitle() {
        linearLayout {
            id(12)
            below(11)
            size(MATCH, WRAP)
            orientation(HORIZONTAL)
            margin(0, dip(12), 0, 0)
            padding(dip(64), dip(8), 0, dip(8))
            backgroundColor(redMarvel)

            textView {
                text(R.string.nome)
                textColor(Color.WHITE)
                textSize(sip(24f))

                typeface(null, Typeface.BOLD)
            }
        }
    }

    private fun renderSummary() {
        linearLayout {
            id(13)
            below(12)
            above(14)
            size(MATCH, MATCH)
            orientation(VERTICAL)

            characters.forEach { character ->
                characterSummaryView {
                    character(character)
                }
            }
        }
    }

    private fun renderButtonButtons() {
        relativeLayout {
            id(14)
            alignParentBottom()
            size(MATCH, WRAP)

            bottomMenu()
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState != oldState
    }

    override fun onChanged(state: AppState) {
//        characters = state.characters.values.toList()
//        Anvil.render()
    }

}