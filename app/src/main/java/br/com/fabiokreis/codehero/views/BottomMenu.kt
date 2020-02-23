package br.com.fabiokreis.codehero.views

import android.graphics.Color
import br.com.fabiokreis.codehero.R
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.dip
import trikita.anvil.DSL.*
import trikita.anvil.cardview.v7.CardViewv7DSL.cardView

object BottomMenu {

    private val redMarvel = Color.parseColor("#D42026")

    fun bottomMenu() {
        cardView {
            size(MATCH, WRAP)
            relativeLayout {
                size(MATCH, WRAP)

                renderLeftArrow()
                renderRightArrow()
            }

        }
    }

    private fun renderLeftArrow() {
        imageView {
            alignParentLeft()
            BaseDSL.size(dip(80), dip(80))
            backgroundResource(R.drawable.baseline_arrow_left_24)
        }
    }

    private fun renderRightArrow() {
        imageView {
            alignParentRight()
            BaseDSL.size(dip(80), dip(80))
            backgroundResource(R.drawable.baseline_arrow_right_24)
        }
    }
}
