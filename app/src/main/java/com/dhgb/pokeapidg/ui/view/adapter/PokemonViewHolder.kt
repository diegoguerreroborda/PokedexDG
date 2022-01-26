package com.dhgb.pokeapidg.ui.view.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.databinding.CardPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CardPokemonBinding.bind(view)

    fun bind(pokemon:PokemonModel){
        //Si quiero poner svg, hay que ver cómo se hace
        Log.d("TAG", "Poner el nombre del pokemon ${pokemon.name} y la foto es ${pokemon.img.other.dreamWorld.front_default}")
        Picasso.get().load(pokemon.img.other.dreamWorld.front_default).into(binding.ivPokemon)
        binding.tvNamePokemon.text = pokemon.name
    }
}