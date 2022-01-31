package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
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

    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        Log.d("fragmmento", "Inicializa PokemonListFragment")
        pokemonViewModel.onCreate()

        pokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        pokemonViewModel.showToast.observe(viewLifecycleOwner, Observer {
            showToast("No se encontraron pokemon")
        })

        pokemonViewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })

        binding.svPokemon.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.i("CALLAPI","Press querysubmit")
                if(!query.isNullOrEmpty()){
                    pokemonViewModel.searchPokemonByName(query.toLowerCase())
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })

        return binding.root
    }

    private fun initRecyclerView(adapterCurrent: List<PokemonModel>) {
        Log.d("POKELIST", "Recycler")
        adapter = PokemonAdapter(adapterCurrent)
        binding.rvPokemonList.layoutManager = null
        binding.rvPokemonList.layoutManager = LinearLayoutManager(context)
        binding.rvPokemonList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Log.d("POKELIST", "Onstart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("POKELIST", "Onresume")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Aqui bindeo los componentes como para poner setOnClickListener
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}