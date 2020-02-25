package br.com.fabiokreis.codehero.views

import android.content.Context
import android.graphics.Typeface
import android.widget.LinearLayout.HORIZONTAL
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.utils.GlideApp
import com.bumptech.glide.request.RequestOptions
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.sip
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.DSL.*
import trikita.anvil.cardview.v7.CardViewv7DSL.cardView

object CardLayout {
    fun cardLayout(context: Context, character: Character) {
        cardView {
            size(MATCH, MATCH)
            margin(dip(1))
            relativeLayout {
                size(MATCH, MATCH)
                orientation(HORIZONTAL)
                gravity(CENTER_VERTICAL)
                padding(dip(18))

                image(context, character.thumbnail?.path, character.thumbnail?.extension)
                cardTitle(character.name)
            }
        }
    }

    private fun image(context: Context, thumbnail: String?, extension: String?) {
        val imageVariant = "/standard_medium"
        val extension = ".$extension"
        val path = "$thumbnail$imageVariant$extension"

        imageView {
            id(1)
            size(dip(64), dip(64))
            alignParentLeft()

            GlideApp
                .with(context)
                .load(path.replaceFirst("http", "https"))
                .fitCenter()
                .apply(RequestOptions.circleCropTransform())
                .into(Anvil.currentView())
        }
    }

    private fun cardTitle(title: String) {
        textView {
            text(title)
            toRightOf(1)
            margin(dip(16), dip(20), 0, 0)
            textSize(sip(18f))
            typeface(null, Typeface.BOLD)
        }
    }
}
