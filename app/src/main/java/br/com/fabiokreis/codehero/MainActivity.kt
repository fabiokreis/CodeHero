package br.com.fabiokreis.codehero

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.fabiokreis.codehero.actions.ActionCreator
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.services.MarvelServiceApi
import com.github.raulccabreu.redukt.states.StateListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        MarvelServiceApi.getCharacters { character, error ->
            println("character $character")
            println("error $error")
        }

        MarvelApplication.redukt.listeners.add(object : StateListener<AppState> {
            override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
                return newState != oldState
            }

            override fun onChanged(state: AppState) {
                println("App state: $state")
            }
        })

        ActionCreator.syncCharacters()
    }
}
