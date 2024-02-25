package io.github.katarem.mtgrestclient.model

import com.google.gson.annotations.SerializedName

data class Card (

    @SerializedName("cardName" ) var cardName : String? = null,
    @SerializedName("img"      ) var img      : String? = null,
    @SerializedName("color"    ) var color    : String?  = null,
    @SerializedName("uuid"     ) var uuid     : String? = null
)
