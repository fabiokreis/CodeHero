package br.com.fabiokreis.codehero.services

import android.util.Log
import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.models.ServerResponse
import br.com.fabiokreis.codehero.models.converters.md5
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

object MarvelServiceApi : BaseApi() {

    private val timeStamp = Timestamp(System.currentTimeMillis()).toString()
    private const val publicKey = "2083364ec81410c9c5586171b24574bb"
    private const val privateKey = "a683175276c0d6600e114eb1aa2fb52f08caac37"

    private val hash = "$timeStamp$privateKey$publicKey".md5()


    override val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MarvelService::class.java)

    fun getCharacters(callback: (character: ServerResponse<Character>?, error: String?) -> Unit) {
        Log.d("teste", "timeStamp: $timeStamp")
        Log.d("teste", "publicKey: $publicKey")
        Log.d("teste", "privateKey: $privateKey")
        Log.d("teste", "hash: $hash")


        service.getCharacters(timeStamp, publicKey, hash, 0, 99).enqueue(handleResponse(callback))
    }
}