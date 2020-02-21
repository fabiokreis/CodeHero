package br.com.fabiokreis.codehero

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.fabiokreis.codehero.services.MarvelServiceApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        MarvelServiceApi.getCharacters { character, error ->
            println("character $character")
            println("error $error")
        }
    }
}
