package io.github.katarem.mtgrestclient.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.katarem.mtgrestclient.api.MTGAPI
import io.github.katarem.mtgrestclient.model.Card
import io.github.katarem.mtgrestclient.model.Deck
import io.github.katarem.mtgrestclient.utils.API_STATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MTGViewModel: ViewModel() {

    val service = MTGAPI.service

    private var _allCards = MutableStateFlow(listOf<Card>())
    val allCards = _allCards.asStateFlow()

    private var _estadoCall = MutableStateFlow(API_STATE.LOADING)
    val estadoCall = _estadoCall.asStateFlow()

    private var _listaOwnedDecks = MutableStateFlow(listOf<Deck>())
    val listaOwnedDecks = _listaOwnedDecks.asStateFlow()

    private var _listaDecks = MutableStateFlow(listOf<Deck>())
    val listaDecks = _listaDecks.asStateFlow()

    fun getAllDecks(){
        viewModelScope.launch(Dispatchers.IO) {
            val respuesta = service.value.getAllDecks()
            if(respuesta.isSuccessful){
                _listaDecks.value = respuesta.body()!!
                _estadoCall.value = API_STATE.SUCCESS
            }
        }
    }

    fun getDecksByUser(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _estadoCall.value = API_STATE.LOADING
            val respuesta = service.value.getUserDecks(id)
            when(respuesta.code()){
                200 -> {
                    Log.d("MTGViewModel", "Se obtuvieron los decks")
                    _listaOwnedDecks.value = respuesta.body()!!
                }
                else -> {
                    Log.d("MTGViewModel", "No se obtuvieron los decks\n ${respuesta.code()}")
                    _listaOwnedDecks.value = arrayListOf()
                }
            }
            _estadoCall.value = API_STATE.SUCCESS
        }
    }

    fun getAllCards(){
        viewModelScope.launch(Dispatchers.IO) {
            val respuesta = service.value.getAllCards()
            if(respuesta.isSuccessful){
                _allCards.value = respuesta.body()!!
            }
        }
    }


}