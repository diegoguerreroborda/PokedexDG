package com.dhgb.pokeapidg.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dhgb.pokeapidg.R
import com.dhgb.pokeapidg.data.model.Stats
import com.dhgb.pokeapidg.databinding.FragmentPokemonDetailBinding
import com.dhgb.pokeapidg.ui.component.ProgressBarAnimation
import com.squareup.picasso.Picasso


class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            val pokemon = PokemonDetailFragmentArgs.fromBundle(it).pokemonModel
            binding.ivWave.setBackgroundResource(changeResourceTypePokemon(pokemon.types[0].type.name))
            Picasso.get().load(pokemon.img.other.dreamWorld.firstImg).into(binding.ivPokemon)
            binding.tvNamePokemon.text = upperCaseFirst(pokemon.name)
            binding.tvBaseExperiencePokemon.text = pokemon.baseExperience.toString()
            binding.tvWeightPokemon.text = "${pokemon.weight} kg"
            binding.tvHeightPokemon.text = "${pokemon.height} m"

            //binding.ivWave.background.setTint(R.drawable.wave_pokemon)
            //binding.ivWave.setBackgroundColor(R.drawable.wave_pokemon_fire)
//            binding.ivWave.setBackgroundColor(changeColorTypePokemon(pokemon.types[0].type.name))
//            binding.ivWave.background.colorFilter = Color.argb(255, 255, 255, 255)
//            R.drawable.wave_pokemon.

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
                    val anim = ProgressBarAnimation(binding.pbHp, Constants.FROM_PROGRESS_BAR, stat.baseStat)
                    binding.pbHp.startAnimation(anim)
                }
                "attack" -> {
                    binding.tvStatAtk.text = stat.baseStat.toString()
                    val anim = ProgressBarAnimation(binding.pbAttack, Constants.FROM_PROGRESS_BAR, stat.baseStat)
                    binding.pbAttack.startAnimation(anim)
                }
                "defense" -> {
                    binding.tvStatDef.text = stat.baseStat.toString()
                    val anim = ProgressBarAnimation(binding.pbDefense, Constants.FROM_PROGRESS_BAR, stat.baseStat)
                    binding.pbDefense.startAnimation(anim)
                }
            }
        }
    }

    private fun changeResourceTypePokemon(type: String): Int {
        Log.d("POKECOLOR", "Cambia color $type")
        when (type) {
            "grass" -> {
                return R.drawable.wave_pokemon_grass
            }
            "electric" -> {
                return R.drawable.wave_pokemon_electric
            }
            "water" -> {
                return R.drawable.wave_pokemon_water
            }
            "fire" -> {
                return R.drawable.wave_pokemon_fire
            }
            "bug" -> {
                return R.drawable.wave_pokemon_bug
            }
            "poison" -> {
                return R.drawable.wave_pokemon_poison
            }
            "normal" -> {
                return R.drawable.wave_pokemon_water
            }
            "ghost" -> {
                return R.drawable.wave_pokemon_water
            }
            "psychic" -> {
                return R.drawable.wave_pokemon_water
            }
            "ground" -> {
                return R.drawable.wave_pokemon_water
            }
            "dark" -> {
                return R.drawable.wave_pokemon_water
            }
            else -> return R.drawable.wave_get_started
        }

    }

    object Constants {
        const val FROM_PROGRESS_BAR = 0
    }
}