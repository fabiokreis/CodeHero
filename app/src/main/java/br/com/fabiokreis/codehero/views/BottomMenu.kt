package br.com.fabiokreis.codehero.views

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout.HORIZONTAL
import br.com.fabiokreis.codehero.Anvil.ReactiveFrameComponent
import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.R
import br.com.fabiokreis.codehero.actions.ActionCreator
import br.com.fabiokreis.codehero.models.AppState
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.dip
import trikita.anvil.DSL.*
import trikita.anvil.cardview.v7.CardViewv7DSL.cardView

inline fun bottomMenu(crossinline func: BottomMenu.() -> Unit) {
    dslAddView(func)
}

class BottomMenu(context: Context) : ReactiveFrameComponent(context) {

    private val redMarvel = Color.parseColor("#D42026")
    private var firstButtonNumber: Int = 1
    private var buttonActive: Int = 1

    override fun view() {
        cardView {
            size(MATCH, WRAP)
            relativeLayout {
                size(MATCH, WRAP)

                renderLeftArrow()
                renderButtons()
                renderRightArrow()
            }
        }
    }

    private fun renderLeftArrow() {
        imageView {
            alignParentLeft()
            size(dip(48), dip(48))
            margin(dip(30), dip(18), 0, dip(24))
            backgroundResource(R.drawable.baseline_arrow_left_24)
            onClick {
                if (firstButtonNumber > 3)
                    firstButtonNumber -= 3
                else if (firstButtonNumber > 1)
                    firstButtonNumber--
                render()
            }
        }
    }

    private fun renderButtons() {
        linearLayout {
            orientation(HORIZONTAL)
            centerInParent()

            val numberOfButtons = MarvelApplication.redukt.state.getNumberOfVisibleButtons(firstButtonNumber)
            val itemsPerPage = MarvelApplication.redukt.state.itemsPerPage

            for (x in firstButtonNumber..firstButtonNumber + numberOfButtons) {
                textView {
                    val active = x == buttonActive
                    text(x.toString())
                    textColor(getTextColor(active))
                    textSize(sip(16f))
                    gravity(CENTER)
                    margin(dip(10), 0, dip(10), 0)
                    backgroundResource(getButtonResource(active))
                    onClick {
                        buttonActive = x
                        ActionCreator.updateOffset((x - 1) * itemsPerPage)
                    }
                }
            }
        }
    }

    private fun renderRightArrow() {
        val state = MarvelApplication.redukt.state
        val numberOfButtons = state.getTotalOfButtons()

        imageView {
            alignParentRight()
            BaseDSL.size(dip(48), dip(48))
            margin(0, dip(18), dip(30), dip(24))
            backgroundResource(R.drawable.baseline_arrow_right_24)
            onClick {
                if (numberOfButtons > firstButtonNumber + 4)
                    firstButtonNumber += 3
                else if (numberOfButtons > firstButtonNumber + 2)
                    firstButtonNumber++
                else if (numberOfButtons == firstButtonNumber + 2 && !state.isResult)
                    ActionCreator.syncCharacters()
                render()
            }
        }
    }

    private fun getButtonResource(active: Boolean): Int {
        return if (active)
            R.drawable.oval_button_red
        else
            R.drawable.oval_button_white
    }

    private fun getTextColor(active: Boolean): Int {
        return if (active)
            Color.WHITE
        else
            redMarvel
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState != oldState
    }

    override fun onChanged(state: AppState) { }
}
