package ru.tidinari.teabush.api

import androidx.annotation.IntRange
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.server.AuthResponse

interface TeabushAPI {

    companion object {
        /**
         * URL сайта API
         */
        const val BASE_URL = "https://tidinari.ru/"
    }

    /**
     * Получить список чая
     *
     * @return список чая, нахоядщийся на сервере
     */
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

    /**
     * Получить список тэгов
     *
     * @return список тэгов с сервера
     */
    @GET("/tags")
    suspend fun getTags(): List<Tag>

    /**
     * Зарегистрировать пользователя
     *
     * @param login логин пользователя
     * @param password захешированный пароль пользователя
     * @return Ответ от сервера о статусе регистрации, токен.
     */
    @POST("/register?login={login}&password={password}")
    suspend fun register(@Path("login") login: String, @Path("password") password: String): AuthResponse


    /**
     * Аутентифицировать пользователья
     *
     * @param login логин пользователя
     * @param password захешированный пароль пользователя
     * @return Ответ от сервера о статусе регистрации, токен.
     */
    @POST("/login?login={login}&password={password}")
    suspend fun login(@Path("login") login: String, @Path("password") password: String): AuthResponse

    /**
     * Получить список избранного конкретного пользователя
     *
     * @param token аутентификация пользователя
     * @return список избранного
     */
    @GET("/favorite")
    suspend fun getFavoriteList(@Header("Authorization") token: String): List<Tea>

    /**
     * Отметить избранное
     *
     * @param token аутентификация пользователя
     * @param id чая, который нужно добавить или убрать из избранного
     * @param isFavorite изменить на избранное или удалить из избранного
     */
    @GET("/favorite/set?id={id}&isFavorite={isFavorite}")
    suspend fun setFavorite(@Header("Authorization") token: String, @Path("id") id: Int, @Path("isFavorite") isFavorite: Boolean)

    /**
     * Отметить избранное
     *
     * @param token аутентификация пользователя
     * @param id чая, который нужно оценить
     * @param rating поставить рейтинг
     */
    @GET("/favorite/set?id={id}&rating={rating}")
    suspend fun setRating(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Path("rating") @IntRange(from = 0, to = 5) rating: Int
    )

    /**
     * Добавить чай на сервер
     *
     * @param token аутентификация пользователя
     * @param tea чай, который будет добавлен в базу данных
     */
    @POST("/tea/add?tea={tea}")
    suspend fun addTea(@Header("Authorization") token: String, @Path("tea") tea: Tea)
}