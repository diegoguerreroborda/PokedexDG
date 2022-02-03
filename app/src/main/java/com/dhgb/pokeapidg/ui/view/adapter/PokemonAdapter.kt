package com.dhgb.pokeapidg.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.pokeapidg.R
import com.dhgb.pokeapidg.data.database.entities.PokemonEntity
import com.dhgb.pokeapidg.data.model.PokemonModel

class PokemonAdapter(
    private val pokemonList: List<PokemonModel>,
    private val itemClickListener: OnItemClickListener
    ): RecyclerView.Adapter<PokemonViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(pokemonModel: PokemonModel, position: Int)
        fun addToFav(pokemonModel: PokemonModel)
        fun removeToFav(pokemonModel: PokemonModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.card_pokemon, parent, false), itemClickListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = pokemonList.size
}