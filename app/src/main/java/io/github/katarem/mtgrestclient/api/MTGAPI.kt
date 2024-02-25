package io.github.katarem.mtgrestclient.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MTGAPI{

    var MODO_EMULADOR = false

    private val retrofit = if(MODO_EMULADOR){
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    else{
        Retrofit.Builder()
            .baseUrl("http://192.168.1.38:8080/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
    val service = lazy {
        retrofit.create(MTGInterface::class.java)
    }
}