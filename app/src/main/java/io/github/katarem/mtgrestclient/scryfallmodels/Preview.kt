package io.github.katarem.mtgrestclient.scryfallmodels

import com.google.gson.annotations.SerializedName

data class Preview (

    @SerializedName("source"       ) var source      : String? = null,
    @SerializedName("source_uri"   ) var sourceUri   : String? = null,
    @SerializedName("previewed_at" ) var previewedAt : String? = null

)