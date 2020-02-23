package br.com.fabiokreis.codehero.views

import android.graphics.Typeface
import android.media.Image
import android.widget.LinearLayout.HORIZONTAL
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.sip
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.DSL.*
import trikita.anvil.cardview.v7.CardViewv7DSL.cardView

object CardLayout {
    fun cardLayout(content: String, thumbnail: Image?) {
        cardView {
            size(MATCH, WRAP)
            margin(dip(1))
            linearLayout {
                size(MATCH, WRAP)
                orientation(HORIZONTAL)
                padding(dip(16))

                image(thumbnail)
                cardTitle(content)
            }
        }
    }

    private fun image(thumbnail: Image?) {
        thumbnail?.let {
            imageView {

            }
        }
    }

    private fun cardTitle(title: String) {
        textView {
            text(title)
            margin(dip(16))
            textSize(sip(24f))
            typeface(null, Typeface.BOLD)
        }
    }
}
