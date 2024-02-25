package io.github.katarem.mtgrestclient.scryfallmodels

import com.google.gson.annotations.SerializedName

data class RelatedUris (

    @SerializedName("gatherer"                    ) var gatherer                  : String? = null,
    @SerializedName("tcgplayer_infinite_articles" ) var tcgplayerInfiniteArticles : String? = null,
    @SerializedName("tcgplayer_infinite_decks"    ) var tcgplayerInfiniteDecks    : String? = null,
    @SerializedName("edhrec"                      ) var edhrec                    : String? = null

)