package com.dhgb.pokeapidg.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dhgb.pokeapidg.data.database.dao.PokemonDao
import com.dhgb.pokeapidg.data.database.entities.PokemonEntity


@Database(
    entities = [PokemonEntity::class],
    version = 6,

)
abstract class PokemonDb : RoomDatabase(){

    abstract fun pokemonDao(): PokemonDao
    object DatabaseProvider {

        private lateinit var dbInstance: PokemonDb

        fun getDataBase(context: Context): PokemonDb {
            if (!this::dbInstance.isInitialized){
                dbInstance = Room.databaseBuilder(context.applicationContext,PokemonDb::class.java,"pokeName")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance
        }
    }
}