package com.dhgb.pokeapidg.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.pokeapidg.core.RetrofitHelper
import com.dhgb.pokeapidg.data.model.DefaultImg
import com.dhgb.pokeapidg.data.model.Other
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.data.model.Sprites
import com.dhgb.pokeapidg.data.network.PokemonAPICLient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val retrofit = RetrofitHelper.getRetrofit()

    val pokemonList = MutableLiveData<List<PokemonModel>>()
    var _pokemonList = ArrayList<PokemonModel>()

    val isLoading = MutableLiveData<Boolean>()

//    val getPokemonUseCase = GetPokemonUseCase()

    fun onCreate() {
        Log.d("viewmodel", "Entro al viewmodelPokemonList")
        getAllPokemon()
    }

    private fun getAllPokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            for (i in 1..25) {
                val call = retrofit.create(PokemonAPICLient::class.java).getPokemon2("$i")
                val eachPokemon = call.body()
                if(call.isSuccessful){
                    if (eachPokemon != null) {
                        _pokemonList.add(PokemonModel(eachPokemon.name, Sprites(Other(DefaultImg(eachPokemon.img.other.dreamWorld.front_default)))))
                        Log.d("nuevo", "Nombre: ${eachPokemon.name}  url: ${eachPokemon.img.other.dreamWorld.front_default}")
                    }
                }
            }
            Log.d("_pokemonList", "${_pokemonList}")
            pokemonList.postValue(_pokemonList)
            isLoading.postValue(false)
        }
    }
}