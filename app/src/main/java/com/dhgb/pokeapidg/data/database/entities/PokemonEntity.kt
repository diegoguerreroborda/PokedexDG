package com.dhgb.pokeapidg.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhgb.pokeapidg.data.model.Sprites

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "pokemonName") val pokemonName: String,
    @ColumnInfo(name = "pokemonImg") val pokemonImg: String,
    )