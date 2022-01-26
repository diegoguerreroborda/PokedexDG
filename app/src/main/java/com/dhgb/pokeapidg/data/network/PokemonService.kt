package com.dhgb.pokeapidg.data.network

import android.util.Log
import com.dhgb.pokeapidg.core.RetrofitHelper
import com.dhgb.pokeapidg.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class PokemonService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemon(): List<PokemonModel> {
        return withContext(Dispatchers.IO) {
            Log.d("viewmodel", "Entra a PokemonService")
            val response = retrofit.create(PokemonAPICLient::class.java).getPokemon("1")
            Log.d("viewmodel", "ds $response.body()")
            response.body() ?: emptyList()
        }
    }
}