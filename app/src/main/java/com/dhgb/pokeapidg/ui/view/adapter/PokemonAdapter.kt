package com.dhgb.pokeapidg.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.pokeapidg.R
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.ui.viewmodel.PokemonViewModel

class PokemonAdapter(val pokemonList:List<PokemonModel>): RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.card_pokemon , parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = pokemonList.size
}