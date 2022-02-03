package com.dhgb.pokeapidg.data.network

import com.dhgb.pokeapidg.core.RetrofitHelper
import com.dhgb.pokeapidg.data.model.DefaultImg
import com.dhgb.pokeapidg.data.model.Other
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.data.model.Sprites

class PokemonService {


    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemon(index: String): PokemonModel? {
        val call = retrofit.create(PokemonAPICLient::class.java).getPokemon("$index")
        val eachPokemon = call.body()
        if(call.isSuccessful){
            if (eachPokemon != null) {
                return PokemonModel(eachPokemon.id, eachPokemon.name, Sprites(Other(DefaultImg(eachPokemon.img.other.dreamWorld.firstImg))),
                        eachPokemon.weight, eachPokemon.height, eachPokemon.baseExperience, eachPokemon.types, eachPokemon.stats)
            }
        }
        return null
    }
}