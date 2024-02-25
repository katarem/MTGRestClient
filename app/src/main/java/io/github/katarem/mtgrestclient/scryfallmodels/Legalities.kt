package io.github.katarem.mtgrestclient.scryfallmodels

import com.google.gson.annotations.SerializedName

data class Legalities (

    @SerializedName("standard"        ) var standard        : String? = null,
    @SerializedName("future"          ) var future          : String? = null,
    @SerializedName("historic"        ) var historic        : String? = null,
    @SerializedName("timeless"        ) var timeless        : String? = null,
    @SerializedName("gladiator"       ) var gladiator       : String? = null,
    @SerializedName("pioneer"         ) var pioneer         : String? = null,
    @SerializedName("explorer"        ) var explorer        : String? = null,
    @SerializedName("modern"          ) var modern          : String? = null,
    @SerializedName("legacy"          ) var legacy          : String? = null,
    @SerializedName("pauper"          ) var pauper          : String? = null,
    @SerializedName("vintage"         ) var vintage         : String? = null,
    @SerializedName("penny"           ) var penny           : String? = null,
    @SerializedName("commander"       ) var commander       : String? = null,
    @SerializedName("oathbreaker"     ) var oathbreaker     : String? = null,
    @SerializedName("standardbrawl"   ) var standardbrawl   : String? = null,
    @SerializedName("brawl"           ) var brawl           : String? = null,
    @SerializedName("alchemy"         ) var alchemy         : String? = null,
    @SerializedName("paupercommander" ) var paupercommander : String? = null,
    @SerializedName("duel"            ) var duel            : String? = null,
    @SerializedName("oldschool"       ) var oldschool       : String? = null,
    @SerializedName("premodern"       ) var premodern       : String? = null,
    @SerializedName("predh"           ) var predh           : String? = null

)