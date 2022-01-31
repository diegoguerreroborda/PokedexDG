package com.dhgb.pokeapidg.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonModel (
        @Expose @SerializedName(Constants.ID) val id: Int,
        @Expose @SerializedName(Constants.NAME) val name: String,
        @Expose @SerializedName(Constants.SPRITES) val img:Sprites,
        @Expose @SerializedName(Constants.WEIGHT) val weight:Float,
        @Expose @SerializedName(Constants.HEIGHT) val height:Float,
        @Expose @SerializedName(Constants.BASE_EXPERIENCE) val baseExperience:Int,
        @Expose @SerializedName(Constants.TYPES) val types:List<Types>,
        @Expose @SerializedName(Constants.STATS) val stats:List<Stats>
): Parcelable

@Parcelize
data class Sprites (
        @SerializedName(Constants.OTHER_SPRITE) val other: Other,
): Parcelable

@Parcelize
data class Other (
        @SerializedName(Constants.HOME_SPRITE) val dreamWorld: DefaultImg,
): Parcelable

@Parcelize
data class DefaultImg (
        @SerializedName(Constants.IMG_MAIN) val firstImg: String,
): Parcelable

@Parcelize
data class Types(
        @SerializedName(Constants.TYPE) val type: Type,
): Parcelable

@Parcelize
data class Type (
        @SerializedName(Constants.NAME) val name: String,
): Parcelable

@Parcelize
data class Stats (
        @SerializedName(Constants.BASE_STAT) val baseStat: Int,
        @SerializedName(Constants.STAT_NAME) val stat: Stat,
): Parcelable

@Parcelize
data class Stat (
        @SerializedName(Constants.NAME) val name: String,
): Parcelable

object Constants {
        const val ID = "id"
        const val NAME = "name"
        const val SPRITES = "sprites"
        const val WEIGHT = "weight"
        const val HEIGHT = "height"
        const val BASE_EXPERIENCE = "base_experience"
        const val TYPES = "types"
        const val STATS = "stats"

        const val OTHER_SPRITE = "other"
        const val HOME_SPRITE = "home"
        const val IMG_MAIN = "front_default"

        const val TYPE = "type"

        const val BASE_STAT = "base_stat"
        const val STAT_NAME = "stat"
}