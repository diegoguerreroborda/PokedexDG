package com.dhgb.pokeapidg.ui.view.adapter

import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.databinding.CardPokemonBinding
import com.dhgb.pokeapidg.ui.view.fragments.PokemonListFragmentDirections
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = CardPokemonBinding.bind(view)

    fun bind(pokemon:PokemonModel){
        Log.d("TAG", "Poner el nombre del pokemon ${pokemon.name} y la foto es ${pokemon.img.other.dreamWorld.firstImg}")
        Picasso.get().load(pokemon.img.other.dreamWorld.firstImg).into(binding.ivPokemon)
        binding.tvIdPokemon.text = "#${pokemon.id}"
        binding.tvNamePokemon.text = upperCaseFirst(pokemon.name)

        clickItem(pokemon)
    }

    private fun clickItem (pokemon:PokemonModel) {
        binding.root.setOnClickListener {
            val direction = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon)
            Navigation.findNavController(binding.root).navigate(direction)
        }
    }

    private fun upperCaseFirst (text: String) : String {
        return text.substring(0,1).toUpperCase() + text.substring(1)
    }
}