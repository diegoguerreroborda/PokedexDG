package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dhgb.pokeapidg.R
import com.dhgb.pokeapidg.data.model.Stats
import com.dhgb.pokeapidg.databinding.FragmentPokemonDetailBinding
import com.dhgb.pokeapidg.ui.component.ProgressBarAnimation
import com.squareup.picasso.Picasso


class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        binding.llBack.setOnClickListener{
            requireActivity().onBackPressed()
        }

        arguments?.let {
            val pokemon = PokemonDetailFragmentArgs.fromBundle(it).pokemonModel
            binding.ivWave.setColorFilter(
                ContextCompat.getColor(
                    requireContext(), changeColorTypePokemon(
                        pokemon.types[0].type.name
                    )
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )
         binding.lottieFav.progress = 0.2f
            Picasso.get().load(pokemon.img.other.dreamWorld.firstImg).into(binding.ivPokemon)
            binding.tvNamePokemon.text = upperCaseFirst(pokemon.name)
            binding.tvBaseExperiencePokemon.text = pokemon.baseExperience.toString()
            binding.tvWeightPokemon.text = "${pokemon.weight} kg"
            binding.tvHeightPokemon.text = "${pokemon.height} m"

            if (pokemon.stats.isNotEmpty()) {
                sortStats(pokemon.stats)
            }
        }
        return binding.root
    }

    private fun upperCaseFirst(text: String): String {
        return text.substring(0, 1).toUpperCase() + text.substring(1)
    }

    private fun sortStats(stats: List<Stats>) {
        for (stat in stats) {
            when (stat.stat.name) {
                "hp" -> {
                    binding.tvStatHp.text = stat.baseStat.toString()
                    val anim = ProgressBarAnimation(
                        binding.pbHp,
                        Constants.FROM_PROGRESS_BAR,
                        stat.baseStat
                    )
                    binding.pbHp.startAnimation(anim)
                }
                "attack" -> {
                    binding.tvStatAtk.text = stat.baseStat.toString()
                    val anim = ProgressBarAnimation(
                        binding.pbAttack,
                        Constants.FROM_PROGRESS_BAR,
                        stat.baseStat
                    )
                    binding.pbAttack.startAnimation(anim)
                }
                "defense" -> {
                    binding.tvStatDef.text = stat.baseStat.toString()
                    val anim = ProgressBarAnimation(
                        binding.pbDefense,
                        Constants.FROM_PROGRESS_BAR,
                        stat.baseStat
                    )
                    binding.pbDefense.startAnimation(anim)
                }
            }
        }
    }

    private fun changeColorTypePokemon(type: String): Int {
        Log.d("POKECOLOR", "Cambia color $type")
        when (type) {
            "grass" -> {
                return R.color.type_grass
            }
            "electric" -> {
                return R.color.type_electric
            }
            "water" -> {
                return R.color.type_water
            }
            "fire" -> {
                return R.color.type_fire
            }
            "bug" -> {
                return R.color.type_bug
            }
            "poison" -> {
                return R.color.type_poison
            }
            "normal" -> {
                return R.color.type_normal
            }
            "ghost" -> {
                return R.color.type_ghost
            }
            "psychic" -> {
                return R.color.type_psychic
            }
            "ground" -> {
                return R.color.type_ground
            }
            "dark" -> {
                return R.color.type_dark
            }
            else -> return R.color.black
        }
    }

    object Constants {
        const val FROM_PROGRESS_BAR = 0
    }

}