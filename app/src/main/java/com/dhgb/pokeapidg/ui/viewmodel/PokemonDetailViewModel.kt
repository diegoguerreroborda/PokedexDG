package com.dhgb.pokeapidg.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhgb.pokeapidg.data.database.PokemonDb
import com.dhgb.pokeapidg.data.database.dao.PokemonDao
import com.dhgb.pokeapidg.data.database.entities.PokemonEntity
import com.dhgb.pokeapidg.data.model.PokemonModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailViewModel: ViewModel() {

    private lateinit var context: Context
    private lateinit var db: PokemonDao

    val showToast = MutableLiveData<Boolean>()

    fun onCreate(contextC: Context) {
        context = contextC
        db = PokemonDb.DatabaseProvider.getDataBase(context).pokemonDao()
    }

    fun addPokemonToDB(pokemonModel: PokemonModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.insertOne(
                PokemonEntity(
                id = pokemonModel.id,
                pokemonName = pokemonModel.name,
                pokemonImg = pokemonModel.img.other.dreamWorld.firstImg)
            )
        }
        showToast.postValue(true)
    }
}