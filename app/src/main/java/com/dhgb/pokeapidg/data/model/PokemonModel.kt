package com.dhgb.pokeapidg.data.model

import com.google.gson.annotations.SerializedName

data class PokemonModel (
        @SerializedName("name") var name: String,
        @SerializedName("sprites") var img:Sprites,
        //@SerializedName("species") var species:Object,
)

data class Sprites (
        @SerializedName("other") val other: Other,
)

data class Other (
        @SerializedName("home") val dreamWorld: DefaultImg,
)

data class DefaultImg (
        @SerializedName("front_default") val front_default: String,
)