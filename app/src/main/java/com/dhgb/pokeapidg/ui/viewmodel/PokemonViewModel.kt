package com.dhgb.pokeapidg.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.data.network.PokemonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val api = PokemonService()

    val pokemonList = MutableLiveData<List<PokemonModel>>()
    var _pokemonList = ArrayList<PokemonModel>()

    val isLoading = MutableLiveData<Boolean>()

    val showToast = MutableLiveData<Boolean>()

    fun onCreate() {
        if(_pokemonList.isEmpty()){
            _pokemonList.clear()
            getAllPokemon()
        }
    }

    private fun getAllPokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            for (i in 1..25) {
                val pokemon = api.getPokemon("$i")
                if(pokemon != null){
                    _pokemonList.add(pokemon!!)
                }else{
                    //La api no responde más
                    //Mostrar view de no internet
                    showToast.postValue(true)
                    isLoading.postValue(false)
                }
            }
            Log.d("POKEVIEWMODEL", "${_pokemonList[0].stats}")
            pokemonList.postValue(_pokemonList)
            isLoading.postValue(false)
        }
    }

    fun searchPokemonByName(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val pokemon = api.getPokemon("$name")
            if(pokemon != null){
                _pokemonList.clear()
                //pokemonList.postValue(emptyList())
                _pokemonList.add(pokemon!!)
                pokemonList.postValue(_pokemonList)
                isLoading.postValue(false)
            }else{
                showToast.postValue(true)
                isLoading.postValue(false)
            }
        }
    }
}