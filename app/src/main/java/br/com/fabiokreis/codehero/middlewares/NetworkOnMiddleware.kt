package br.com.fabiokreis.codehero.middlewares

import android.content.Context
import br.com.fabiokreis.codehero.models.AppState
import br.com.fabiokreis.codehero.utils.NetworkUtils
import com.github.raulccabreu.redukt.middlewares.BaseAnnotatedMiddleware

abstract class NetworkOnMiddleware(private val context: Context):
    BaseAnnotatedMiddleware<AppState>(){

    override fun canExecute(state: AppState): Boolean {
        return NetworkUtils.isOnline(context)
    }

}