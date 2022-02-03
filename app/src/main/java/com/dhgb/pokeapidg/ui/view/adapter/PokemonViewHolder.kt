package com.dhgb.pokeapidg.ui.view.adapter

import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.databinding.CardPokemonBinding
import com.dhgb.pokeapidg.ui.view.adapter.PokemonAdapter.OnItemClickListener
import com.dhgb.pokeapidg.ui.view.fragments.PokemonListFragmentDirections
import com.squareup.picasso.Picasso

class PokemonViewHolder(
    view: View,
    private val itemClickListener: OnItemClickListener): RecyclerView.ViewHolder(view) {

    private val binding = CardPokemonBinding.bind(view)

    fun bind(pokemon:PokemonModel, position: Int){
        binding.lottieFav.progress = 0.2f
        Log.d("TAG", "Poner el nombre del pokemon ${pokemon.name} y la foto es ${pokemon.img.other.dreamWorld.firstImg}")
        Picasso.get().load(pokemon.img.other.dreamWorld.firstImg).into(binding.ivPokemon)
        binding.tvIdPokemon.text = "#${pokemon.id}"
        binding.tvNamePokemon.text = upperCaseFirst(pokemon.name)

        binding.root.setOnClickListener {
            itemClickListener.onItemClick(pokemon, position)
        }
        clickFav(pokemon, position)
    }

    private fun clickFav(pokemon:PokemonModel, position: Int) {
        binding.lottieFav.setOnClickListener{
            Log.d("CLICKFAV", "presiono fav a ${pokemon.name}")
            if(binding.lottieFav.progress == 0.2f){
                binding.lottieFav.speed = 2f
                binding.lottieFav.playAnimation()
                itemClickListener.addToFav(pokemon)
                itemClickListener.removeToFav(pokemon, position)
            }else{
                binding.lottieFav.progress = 0.2f
            }
        }
    }

    private fun clickItem (pokemon:PokemonModel) {

    }


    private fun upperCaseFirst (text: String) : String {
        return text.substring(0,1).toUpperCase() + text.substring(1)
    }
}