package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhgb.pokeapidg.databinding.FragmentPokemonDetailBinding
import com.squareup.picasso.Picasso

class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            binding.tvNamePokemon.text = PokemonDetailFragmentArgs.fromBundle(it).namePokemon
            Picasso.get().load(PokemonDetailFragmentArgs.fromBundle(it).imgPokemon).into(binding.ivPokemon)
        }

        return binding.root
    }
}