package ru.tidinari.teabush.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.server.AuthResponse

interface TeabushAPI {
    @GET("/tea/{page}")
    suspend fun getTeaList(@Path("page") page: Int): List<Tea>

    @POST("/register/{login}/{password}")
    suspend fun register(@Path("login") login: String, @Path("password") password: String): AuthResponse

    @GET("/favorite")
    suspend fun getFavoriteList(): List<Tea>
}