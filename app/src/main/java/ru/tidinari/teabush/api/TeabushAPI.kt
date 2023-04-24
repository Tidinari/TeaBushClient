package ru.tidinari.teabush.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.server.AuthResponse

interface TeabushAPI {

    companion object {
        const val BASE_URL = "https://tidinari.ru/"
    }

    @GET("/tea")
    suspend fun getTeaList(): List<Tea>

//    @GET("/tea/{page}")
//    suspend fun getTeaList(@Path("page") page: Int): List<Tea>
//
//    @GET("/tea/search?page={page}&queue={query}")
//    suspend fun getTeaList(@Path("page") page: Int, @Path("query") query: String): List<Tea>
//
//    @GET("/tea/search?page={page}&tag={tag}")
//    suspend fun getTeaList(@Path("page") page: Int, @Path("tag") tags: List<Int>): List<Tea>
//
//    @GET("/tea/search?page={page}&tag={tag}&queue={query}")
//    suspend fun getTeaList(@Path("page") page: Int, @Path("tag") tags: List<Int>, @Path("query") query: String): List<Tea>

    @GET("/tags")
    suspend fun getTags(): List<Tag>

    @POST("/register?login={login}&password={password}")
    suspend fun register(@Path("login") login: String, @Path("password") password: String): AuthResponse

    @POST("/login?login={login}&password={password}")
    suspend fun login(@Path("login") login: String, @Path("password") password: String): AuthResponse

    @GET("/favorite")
    suspend fun getFavoriteList(@Header("Authorization") token: String): List<Tea>

    @GET("/favorite/set?id={id}&isFavorite={isFavorite}")
    suspend fun setFavorite(@Header("Authorization") token: String, @Path("id") id: Int, @Path("isFavorite") isFavorite: Boolean): List<Tea>

    @POST("/tea/add?tea={tea}")
    suspend fun addTea(@Header("Authorization") token: String, @Path("tea") tea: Tea)
}