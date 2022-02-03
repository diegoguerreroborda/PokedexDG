package com.dhgb.pokeapidg.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhgb.pokeapidg.R
import com.dhgb.pokeapidg.data.database.PokemonDb
import com.dhgb.pokeapidg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener{
            //Ir al home
            startActivity(Intent(this, HomeActivity::class.java))
        }

        val db = PokemonDb.DatabaseProvider.getDataBase(baseContext).pokemonDao()
    }
}