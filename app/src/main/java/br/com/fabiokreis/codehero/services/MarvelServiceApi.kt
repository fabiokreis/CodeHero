package br.com.fabiokreis.codehero.services

import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.models.Response
import br.com.fabiokreis.codehero.models.converters.md5
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

object MarvelServiceApi : BaseApi() {

    private val timeStamp = Timestamp(System.currentTimeMillis()).toString()
    private const val publicKey = "2083364ec81410c9c5586171b24574bb"
    private const val privateKey = "a683175276c0d6600e114eb1aa2fb52f08caac37"

    private val hash = "$timeStamp$privateKey$publicKey".md5()


    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    override val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MarvelService::class.java)

    fun getCharacters(
        offset: Int,
        callback: (character: Response<Character>?, error: String?) -> Unit
    ) {
        service.getCharacters(timeStamp, publicKey, hash, (offset * 100), limit = 100)
            .enqueue(handleResponse(callback))
    }

    fun getSearchCharacters(
        name: String,
        callback: (character: Response<Character>?, error: String?) -> Unit
    ) {
        service.getCharacter(timeStamp, publicKey, hash, name).enqueue(handleResponse(callback))
    }
}