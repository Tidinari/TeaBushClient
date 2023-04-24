package ru.tidinari.teabush.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.server.AuthResponse

interface TeabushAPI {

    companion object {
        const val BASE_URL = "https://tidinari.ru/"
    }

    @GET("/tea/{page}")
    suspend fun getTeaList(@Path("page") page: Int): List<Tea>

    @GET("/tea/search?page={page}&queue={query}")
    suspend fun getTeaList(@Path("page") page: Int, @Path("query") query: String): List<Tea>

    @GET("/tea/search?page={page}&tag={tag}")
    suspend fun getTeaList(@Path("page") page: Int, @Path("tag") tags: List<Int>): List<Tea>

    @GET("/tea/search?page={page}&tag={tag}&queue={query}")
    suspend fun getTeaList(@Path("page") page: Int, @Path("tag") tags: List<Int>, @Path("query") query: String): List<Tea>

    @GET("/tea/tags")
    suspend fun getTags(): List<Tag>

    @POST("/register?login={login}&password={password}")
    suspend fun register(@Path("login") login: String, @Path("password") password: String): AuthResponse

    @GET("/favorite")
    suspend fun getFavoriteList(): List<Tea>
}