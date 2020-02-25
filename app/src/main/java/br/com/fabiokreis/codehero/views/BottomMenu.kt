package br.com.fabiokreis.codehero.views

import android.graphics.Color
import android.widget.LinearLayout.HORIZONTAL
import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.R
import trikita.anvil.Anvil.render
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.dip
import trikita.anvil.DSL.*
import trikita.anvil.cardview.v7.CardViewv7DSL.cardView

object BottomMenu {

    private val redMarvel = Color.parseColor("#D42026")
    private var firstButtonNumber: Int = 1
    private var buttonActive: Int = 1

    private var resultCallback: ((Int) -> Unit)? = null

    fun bottomMenu(result: Unit) {
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
                if (firstButtonNumber > 1)
                    firstButtonNumber--
                render()
            }
        }
    }

    private fun renderButtons() {
        linearLayout {
            orientation(HORIZONTAL)
            centerInParent()

            for (x in firstButtonNumber..firstButtonNumber + 2) {
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
                        resultCallback?.invoke((buttonActive - 1) * 4)
                        render()
                    }
                }
            }
        }
    }

    private fun renderRightArrow() {
        val state = MarvelApplication.redukt.state
        val total: Int = state.getTotalCharacters(state)
        val numberOfButtons: Int = if ((total % 4) == 0) total / 4 else (total / 4) + 1

        imageView {
            alignParentRight()
            BaseDSL.size(dip(48), dip(48))
            margin(0, dip(18), dip(30), dip(24))
            backgroundResource(R.drawable.baseline_arrow_right_24)
            onClick {
                if (numberOfButtons > firstButtonNumber + 2)
                    firstButtonNumber++
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

    fun result(callback: (Int) -> Unit) {
        resultCallback = callback
    }
}
