package ru.tidinari.teabush

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tidinari.teabush.api.TeabushAPI

/**
 * Application - объект, ассоциированный с процессом приложения
 * Один на всё приложение, инициализируется первым
 *
 * @constructor Создат apiService Teabush
 */
class TeabushApp: Application() {
    companion object {
        /**
         * Объект API сервиса Teabush
         */
        val apiService: TeabushAPI = Retrofit.Builder()
            .run {
                baseUrl(TeabushAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }.create(TeabushAPI::class.java)
    }
}