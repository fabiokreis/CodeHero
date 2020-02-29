package br.com.fabiokreis.codehero.Anvil

import android.os.Handler
import android.os.Looper
import android.view.View
import trikita.anvil.Anvil

object LayoutComponentHelper {
    fun render(view: View, uiHandler: Handler, runnable: () -> Unit) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            uiHandler.removeCallbacksAndMessages(null)
            uiHandler.post(runnable)
            return
        }

        Anvil.render(view)
    }
}