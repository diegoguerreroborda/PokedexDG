package com.dhgb.pokeapidg.data.network

import com.dhgb.pokeapidg.data.model.PokemonModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonAPICLient {

    @GET
    suspend fun getPokemon(@Url url: String): Response<PokemonModel>

    @GET("{dexNumOrName}/")
    fun getPokemonByDexNumOrName(@Path("dexNumOrName") dexNumOrName: String?): Call<PokemonModel?>?

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonModel>
}