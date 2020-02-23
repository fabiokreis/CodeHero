package br.com.fabiokreis.codehero.views.character

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.R
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.views.BottomMenu.bottomMenu
import br.com.fabiokreis.codehero.views.BottomMenu.result
import br.com.fabiokreis.codehero.views.ReactRenderableView
import trikita.anvil.Anvil.render
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.*

class CharacterLayout(context: Context) : ReactRenderableView(context) {

    private var characters = listOf<Character>()
    private var name: String = ""
    private var offset: Int = 0

    private val redMarvel = Color.parseColor("#D42026")

    override fun view() {
        focusableInTouchMode(true)
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

            linearLayout {
                orientation(HORIZONTAL)
                size(MATCH, WRAP)
                weightSum(1f)

                val state = MarvelApplication.redukt.state

                editText {
                    BaseDSL.weight(.8f)
                    backgroundResource(R.drawable.rounded_edit_text)
                    text(name)
                    textColor(redMarvel)
                    onTextChanged { name = it.toString() }
                }

                textView {
                    BaseDSL.weight(.2f)
                    size(0, MATCH)
                    backgroundColor(redMarvel)
                    margin(0, 0, 10, 0)
                    gravity(CENTER)
                    textColor(Color.WHITE)
                    BaseDSL.textSize(20.0f)
                    text(R.string.pesquisar)
                    onClick {
                        characters = state.search(state, name) ?: state.characters.values.toList()

                    }
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

            characters.forEachIndexed() { index, character ->
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

            bottomMenu(
                result { offset = it },
                characters.count()
            )

            val state = MarvelApplication.redukt.state
            characters = state.filteredCharactersList(state, offset) ?: state.characters.values.toList()
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState != oldState
    }

    override fun onChanged(state: AppState) {
        characters = state.characters.values.toList()
        render()
    }

}