package br.com.fabiokreis.codehero.views.character

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.R
import br.com.fabiokreis.codehero.extensions.onEnterKeyPressed
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.views.BottomMenu.bottomMenu
import br.com.fabiokreis.codehero.views.BottomMenu.result
import br.com.fabiokreis.codehero.views.ReactRenderableView
import trikita.anvil.Anvil.render
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.*
import java.util.*

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
                textSize(sip(16f))
                typeface("Roboto-Black.ttf")
            }

            textView {
                text(R.string.teste)
                textColor(redMarvel)
                textSize(sip(16f))
                typeface("Roboto-Light.ttf")
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

            val state = MarvelApplication.redukt.state

            textView {
                text(R.string.nome_personagem)
                textColor(redMarvel)
                textSize(sip(14f))
                typeface(null, Typeface.BOLD)
            }
            editText {
                maxLines(1)
                padding(dip(8))
                imeOptions(EditorInfo.IME_ACTION_SEARCH)
                inputType(InputType.TYPE_CLASS_TEXT)
                backgroundResource(R.drawable.rounded_edit_text)
                text(name)
                textColor(redMarvel)
                onTextChanged { name = it.toString() }
                hint(R.string.pesquisar)
                onEnterKeyPressed {
                    characters = state.search(state, name) ?: state.characters.values.toList()
                    render()
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
            padding(dip(80), dip(8), 0, dip(8))
            backgroundColor(redMarvel)

            textView {
                text(R.string.nome)
                textColor(Color.WHITE)
                textSize(sip(16f))

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
            weightSum(1f)

            characters.forEach { character ->
                characterSummaryView {
                    weight(.25f)
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

            bottomMenu(result { offset = it })

            filterCharacters()
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState != oldState
    }

    override fun onChanged(state: AppState) {
        filterCharacters(state)
        render()
    }

    private fun filterCharacters(state: AppState? = null) {
        val state = state ?: MarvelApplication.redukt.state
        characters = state.filteredCharactersList(state, offset) ?: state.characters.values.toList()
    }

}
