package com.dhgb.pokeapidg.data.database.dao

import androidx.room.*
import com.dhgb.pokeapidg.data.database.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table ORDER BY pokemonName DESC")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokemonList:List<PokemonEntity>)

    @Insert()
    suspend fun insertOne(pokemon: PokemonEntity)

    @Delete()
    suspend fun delete(pokemon: PokemonEntity)

//    @Query("DELETE :pokemonName FROM ")
//    suspend fun deleteByName(pokemonName: String)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()
}