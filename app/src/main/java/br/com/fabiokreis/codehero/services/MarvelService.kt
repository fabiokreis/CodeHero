package br.com.fabiokreis.codehero.services

import br.com.fabiokreis.codehero.models.Character
import br.com.fabiokreis.codehero.models.ServerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<ServerResponse<Character>>

}
