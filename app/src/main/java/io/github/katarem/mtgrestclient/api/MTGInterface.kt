package io.github.katarem.mtgrestclient.api

import io.github.katarem.mtgrestclient.model.Card
import io.github.katarem.mtgrestclient.model.Deck
import io.github.katarem.mtgrestclient.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MTGInterface {

    @GET("decks/all")
    @Headers("Accept: application/json")
    suspend fun getAllDecks(): Response<List<Deck>>

    @GET("cards/{id}")
    @Headers("Accept: application/json")
    suspend fun getCardById(@Path("id") id: String): Response<Card>

    @GET("decks/{id}")
    @Headers("Accept: application/json")
    suspend fun getDeckById(@Path("id") id: Int): Response<Deck>

    @GET("decks/by-user/{id}")
    @Headers("Accept: application/json")
    suspend fun getUserDecks(@Path("id") id: Int): Response<List<Deck>>

    @POST("users/register")
    @Headers("Accept: application/json")
    suspend fun registerUser(@Body user: User): Response<User>

    @GET("users/login")
    @Headers("Accept: application/json")
    suspend fun loginUser(@Header("user-name") username: String, @Header("password") password: String): Response<User>

    @PATCH("users/{id}")
    @Headers("Accept: application/json")
    suspend fun  changeProfilePhoto(@Path("id") id: Int, @Header("img") img: String): Response<User>

}