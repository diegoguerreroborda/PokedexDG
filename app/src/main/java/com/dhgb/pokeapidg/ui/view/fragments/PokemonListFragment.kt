package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhgb.pokeapidg.data.model.PokemonModel
import com.dhgb.pokeapidg.databinding.FragmentPokemonListBinding
import com.dhgb.pokeapidg.ui.view.adapter.PokemonAdapter
import com.dhgb.pokeapidg.ui.view.adapter.PokemonAdapter.OnItemClickListener
import com.dhgb.pokeapidg.ui.viewmodel.PokemonListViewModel


class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        Log.d("fragmmento", "Inicializa PokemonListFragment")
        pokemonListViewModel.onCreate(binding.root.context)

        pokemonListViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        pokemonListViewModel.showToast.observe(viewLifecycleOwner, Observer {
            showToast("No se encontraron pokemon")
        })

        pokemonListViewModel.mutablePokemonList.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })

        binding.svPokemon.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isNullOrEmpty()) {
                    pokemonListViewModel.searchPokemonByName(query.toLowerCase())
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
        adapter = PokemonAdapter(adapterCurrent, object : OnItemClickListener {
            override fun onItemClick(pokemonModel: PokemonModel, position: Int) {
                val direction = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemonModel)
                Navigation.findNavController(binding.root).navigate(direction)
            }

            override fun addToFav(pokemonModel: PokemonModel) {
                pokemonListViewModel.addPokemonToDB(pokemonModel)
            }

            override fun removeToFav(pokemonModel: PokemonModel, position: Int) {
                Log.d("REMOVE", "remover del listView")
            }
        }, 0.2f)
//        binding.rvPokemonList.layoutManager = null
        binding.rvPokemonList.layoutManager = LinearLayoutManager(context)
        binding.rvPokemonList.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Aqui bindeo los componentes como para poner setOnClickListener
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}