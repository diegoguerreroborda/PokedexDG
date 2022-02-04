package com.dhgb.pokeapidg.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.pokeapidg.data.database.PokemonDb
import com.dhgb.pokeapidg.data.database.dao.PokemonDao
import com.dhgb.pokeapidg.data.database.entities.PokemonEntity
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.data.network.PokemonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PokemonListViewModel() : ViewModel() {

    private val api = PokemonService()
    private lateinit var context: Context
    private lateinit var db: PokemonDao

    val mutablePokemonList = MutableLiveData<List<PokemonModel>>()
    var pokemonList = ArrayList<PokemonModel>()

    val isLoading = MutableLiveData<Boolean>()

    val showToast = MutableLiveData<Boolean>()

    fun onCreate(contextC: Context) {
        context = contextC
        db = PokemonDb.DatabaseProvider.getDataBase(context).pokemonDao()
        if(pokemonList.isEmpty()){
            pokemonList.clear()
            getAllPokemon()
        }
    }

    private fun getAllPokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            for (i in 1..25) {
                val pokemon = api.getPokemon("$i")
                if(pokemon != null){
                    pokemonList.add(pokemon!!)
                }else{
                    //La api no responde más
                    //Mostrar view de no internet
                    showToast.postValue(true)
                    isLoading.postValue(false)
                }
            }
            Log.d("POKEVIEWMODEL", "${pokemonList[0].stats}")
            mutablePokemonList.postValue(pokemonList)
            isLoading.postValue(false)
        }
    }

    fun searchPokemonByName(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val pokemon = api.getPokemon("$name")
            if(pokemon != null){
                pokemonList.clear()
                //mutablePokemonList.postValue(emptyList())
                pokemonList.add(pokemon!!)
                mutablePokemonList.postValue(pokemonList)
                isLoading.postValue(false)
            }else{
                showToast.postValue(true)
                isLoading.postValue(false)
            }
        }
    }

    fun addPokemonToDB(pokemonModel: PokemonModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.insertOne(PokemonEntity(
                id = pokemonModel.id,
                pokemonName = pokemonModel.name,
                pokemonImg = pokemonModel.img.other.dreamWorld.firstImg))
        }
    }
}