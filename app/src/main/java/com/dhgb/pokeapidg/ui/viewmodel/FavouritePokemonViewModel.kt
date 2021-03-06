package com.dhgb.pokeapidg.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.pokeapidg.data.database.PokemonDb
import com.dhgb.pokeapidg.data.database.dao.PokemonDao
import com.dhgb.pokeapidg.data.database.entities.PokemonEntity
import com.dhgb.pokeapidg.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritePokemonViewModel: ViewModel() {

    private lateinit var context: Context
    private lateinit var db: PokemonDao

    private val types: List<Types> = listOf(Types(Type("type1")), Types(Type("type2")))
    private val stats: List<Stats> = listOf(Stats(1, Stat("stat1")))

    val isLoading = MutableLiveData<Boolean>()
    val mutablePokemonList = MutableLiveData<List<PokemonModel>>()

    var pokemonList = ArrayList<PokemonModel>()

    fun onCreate(contextC: Context) {
        context = contextC
        db = PokemonDb.DatabaseProvider.getDataBase(context).pokemonDao()
        getAllPokemon()
    }

    private fun getAllPokemon() {
        pokemonList.clear()
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val mutablemutablePokemonListDb = db.getAllPokemon()
            if(mutablemutablePokemonListDb.isNotEmpty()){
                for (itemEntity in db.getAllPokemon()){
                    Log.d("ENTITY", "item id es ${itemEntity.id}")
                    pokemonList.add(PokemonModel(itemEntity.id, itemEntity.pokemonName,
                        Sprites(Other(
                        DefaultImg(itemEntity.pokemonImg)
                    )), 50f, 50f, 50, types, stats))
                }
                mutablePokemonList.postValue(pokemonList)
                isLoading.postValue(false)
            }else{
                //Está vacio
                isLoading.postValue(false)
            }

        }
    }

    fun removePokemonToDB(pokemonModel: PokemonModel) {
        Log.d("BORRAR", "borrar a ${pokemonModel.name}")
        CoroutineScope(Dispatchers.IO).launch {
            db.delete(
                PokemonEntity(
                    id = pokemonModel.id,
                    pokemonName = pokemonModel.name,
                    pokemonImg = pokemonModel.img.other.dreamWorld.firstImg
                )
            )
        }
        pokemonList.remove(pokemonModel)
        mutablePokemonList.postValue(pokemonList)
    }
}