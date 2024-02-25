package io.github.katarem.mtgrestclient.scryfallmodels

import com.google.gson.annotations.SerializedName

data class PurchaseUris (

    @SerializedName("tcgplayer"   ) var tcgplayer   : String? = null,
    @SerializedName("cardmarket"  ) var cardmarket  : String? = null,
    @SerializedName("cardhoarder" ) var cardhoarder : String? = null
)
