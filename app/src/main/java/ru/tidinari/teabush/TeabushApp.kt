package ru.tidinari.teabush

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tidinari.teabush.api.TeabushAPI

class TeabushApp: Application() {

    companion object {
        val apiService = Retrofit.Builder()
            .run {
                baseUrl(TeabushAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }.create(TeabushAPI::class.java)
    }

    override fun onCreate() {
        super.onCreate()
    }

}