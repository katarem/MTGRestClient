package io.github.katarem.mtgrestclient.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("profileImg") var profileImg: String? = null,
    @SerializedName("password") var password: String? = null

)
