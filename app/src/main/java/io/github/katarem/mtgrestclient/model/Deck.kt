package io.github.katarem.mtgrestclient.model

import com.google.gson.annotations.SerializedName

data class Deck(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("deckName") var deckName: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("color") var color: String? = null,
    @SerializedName("cards") var cards: ArrayList<Card> = arrayListOf()

){
    fun getCardsByUUID(): Set<CardInfo> {
        val uuidCountMap = cards
            .groupingBy { it.uuid }
            .eachCount()

        return cards
            .filter { (uuidCountMap[it.uuid] ?: 0) > 1 }
            .map{ CardInfo(uuidCountMap[it.uuid]!!,it) }
            .toSet()
    }
}