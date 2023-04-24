package ru.tidinari.teabush

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tidinari.teabush.api.TeabushAPI

class TeabushApp: Application() {

    companion object {
        val apiService: TeabushAPI = Retrofit.Builder()
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