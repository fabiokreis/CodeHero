package br.com.fabiokreis.codehero.extensions

import android.widget.EditText
import trikita.anvil.Anvil

inline fun onEnterKeyPressed(crossinline func: () -> Unit) {
    val view: EditText = Anvil.currentView()
    view.setOnEditorActionListener { _, _, _ ->
        func.invoke()
        true
    }
}