package br.com.fabiokreis.codehero.views.character

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import br.com.fabiokreis.codehero.Anvil.ReactiveFrameComponent
import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.R
import br.com.fabiokreis.codehero.actions.ActionCreator
import br.com.fabiokreis.codehero.extensions.onEnterKeyPressed
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.views.bottomMenu
import br.com.fabiokreis.codehero.views.dslAddView
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.*

inline fun characterLayout(crossinline func: CharacterLayout.() -> Unit) {
    dslAddView(func)
}

class CharacterLayout(context: Context) : ReactiveFrameComponent(context) {

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
                    if (name.isEmpty())
                        ActionCreator.clearSearch()
                    else
                        ActionCreator.searchQuery(this.name)

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

            bottomMenu {
                if (offset == 0) setButtonActive(1)
                result { offset = it }
                this@CharacterLayout.render()
            }

            filterCharacters(MarvelApplication.redukt.state)
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState.characters != oldState.characters
                || newState.searchResult != oldState.searchResult
    }

    override fun onChanged(state: AppState) {
        if (state.isResult) offset = 0
        filterCharacters(state)
    }

    private fun filterCharacters(state: AppState? = null) {
        state ?: return
        characters = state.filteredCharactersList(offset) ?: state.characters.values.toList()
        render()
    }

}
