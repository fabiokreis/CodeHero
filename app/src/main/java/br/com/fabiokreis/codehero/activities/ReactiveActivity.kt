package br.com.fabiokreis.codehero.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.fabiokreis.codehero.MarvelApplication
import br.com.fabiokreis.codehero.models.AppState
import com.github.raulccabreu.redukt.states.StateListener

abstract class ReactiveActivity: AppCompatActivity(), StateListener<AppState> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(render())

        initialState()
    }

    override fun onStart() {
        super.onStart()
        MarvelApplication.redukt.listeners.add(this)
    }

    override fun onStop() {
        MarvelApplication.redukt.listeners.remove(this)
        super.onStop()
    }

    abstract fun initialState()

    abstract fun render(): View

}
