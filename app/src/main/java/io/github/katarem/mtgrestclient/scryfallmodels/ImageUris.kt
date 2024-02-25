package io.github.katarem.mtgrestclient.scryfallmodels

import com.google.gson.annotations.SerializedName


data class ImageUris (

    @SerializedName("small"       ) var small      : String? = null,
    @SerializedName("normal"      ) var normal     : String? = null,
    @SerializedName("large"       ) var large      : String? = null,
    @SerializedName("png"         ) var png        : String? = null,
    @SerializedName("art_crop"    ) var artCrop    : String? = null,
    @SerializedName("border_crop" ) var borderCrop : String? = null

)