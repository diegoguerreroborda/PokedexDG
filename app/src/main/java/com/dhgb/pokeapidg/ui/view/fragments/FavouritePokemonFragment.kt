package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.databinding.FragmentFavouritePokemonBinding
import com.dhgb.pokeapidg.ui.view.adapter.PokemonAdapter
import com.dhgb.pokeapidg.ui.viewmodel.FavouritePokemonViewModel

class FavouritePokemonFragment : Fragment() {

    private var _binding: FragmentFavouritePokemonBinding? = null
    private val binding get() = _binding!!

    private val favouritePokemonViewModel: FavouritePokemonViewModel by viewModels()

    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentFavouritePokemonBinding.inflate(inflater, container, false)

        favouritePokemonViewModel.onCreate(binding.root.context)

        favouritePokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        favouritePokemonViewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })

        return binding.root
    }

    private fun initRecyclerView(adapterCurrent: List<PokemonModel>) {
        adapter = PokemonAdapter(adapterCurrent, object : PokemonAdapter.OnItemClickListener {
            override fun onItemClick(pokemonModel: PokemonModel, position: Int) {
                Log.d("TODO", "click al item")
            }

            override fun addToFav(pokemonModel: PokemonModel) {
                //Nada
            }

            override fun removeToFav(pokemonModel: PokemonModel, position: Int) {
                Log.d("BORRAR", "Aqui comienza")
                favouritePokemonViewModel.removePokemonToDB(pokemonModel)
//                adapter.notifyItemRemoved(position)
            }
        })
        binding.rvPokemonList.layoutManager = LinearLayoutManager(context)
        binding.rvPokemonList.adapter = adapter
    }
}