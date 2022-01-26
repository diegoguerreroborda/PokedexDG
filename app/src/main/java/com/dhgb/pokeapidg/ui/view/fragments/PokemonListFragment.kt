package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.databinding.FragmentPokemonListBinding
import com.dhgb.pokeapidg.ui.viewmodel.PokemonViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhgb.pokeapidg.ui.view.adapter.PokemonAdapter


class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val pokemonViewModel: PokemonViewModel by viewModels()

    private val pokemonList = mutableListOf<PokemonModel>()

    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        Log.d("fragmmento", "Inicializa PokemonListFragment")
        pokemonViewModel.onCreate()

        pokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        pokemonViewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            Log.i("TAG", "gola $it")
            initRecyclerView(it)
        })

        return binding.root
    }

    private fun initRecyclerView(adapter2: List<PokemonModel>) {
        adapter = PokemonAdapter(adapter2)
        binding.rvPokemonList.layoutManager = LinearLayoutManager(context)
        binding.rvPokemonList.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Aqui bindeo los componentes como para poner setOnClickListener
    }
}