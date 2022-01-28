package com.dhgb.pokeapidg.data.model

import com.google.gson.annotations.SerializedName

data class PokemonModel (
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("sprites") var img:Sprites,
        @SerializedName("weight") val weight:Float,
        @SerializedName("height") var height:Float,
        @SerializedName("base_experience") var baseExperience:Int,
        @SerializedName("types") var types:List<Types>
)

data class Sprites (
        @SerializedName("other") val other: Other,
)

data class Other (
        @SerializedName("home") val dreamWorld: DefaultImg,
)

data class DefaultImg (
        @SerializedName("front_default") val firstImg: String,
)

data class Types(
        @SerializedName("type") val type: Type,
)

data class Type (
        @SerializedName("name") val name: String,
)