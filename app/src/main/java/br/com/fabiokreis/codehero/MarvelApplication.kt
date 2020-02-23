package br.com.fabiokreis.codehero

import android.app.Application
import android.content.Context
import br.com.fabiokreis.codehero.middlewares.CharactersMiddleware
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.reducers.CharactersReducer
import com.github.raulccabreu.redukt.Redukt

class MarvelApplication : Application() {

    companion object {
        lateinit var redukt: Redukt<AppState>
    }

    private fun initRedukt(context: Context, appState: AppState): Redukt<AppState> {
        val redukt = Redukt(appState, true)

        addReducers(redukt)
        addMiddlewares(context, redukt)

        return redukt
    }

    private fun addReducers(redukt: Redukt<AppState>) {
        redukt.reducers["characters_reducer"] = CharactersReducer()
    }

    private fun addMiddlewares(context: Context, redukt: Redukt<AppState>) {
        redukt.middlewares["characters_middleware"] = CharactersMiddleware(context)
    }

    override fun onCreate() {
        super.onCreate()

        initRedukt(this, AppState()).let {
            redukt = it
        }
    }
}
