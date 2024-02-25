package io.github.katarem.mtgrestclient.scryfallmodels

import com.google.gson.annotations.SerializedName

data class Prices (

    @SerializedName("usd"        ) var usd       : String? = null,
    @SerializedName("usd_foil"   ) var usdFoil   : String? = null,
    @SerializedName("usd_etched" ) var usdEtched : String? = null,
    @SerializedName("eur"        ) var eur       : String? = null,
    @SerializedName("eur_foil"   ) var eurFoil   : String? = null,
    @SerializedName("tix"        ) var tix       : String? = null

)