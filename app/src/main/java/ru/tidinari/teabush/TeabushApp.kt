package ru.tidinari.teabush

import android.app.Application
import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tidinari.teabush.api.TeabushAPI
import ru.tidinari.teabush.data.server.BasicAuthInterceptor

class TeabushApp: Application() {

    companion object {
        val apiService: TeabushAPI = Retrofit.Builder()
            .run {
                baseUrl(TeabushAPI.BASE_URL)
                    .client(
                        OkHttpClient()
                            .newBuilder()
                            .addInterceptor(BasicAuthInterceptor())
                            .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }.create(TeabushAPI::class.java)

        var token: String = ""
    }

    override fun onCreate() {
        super.onCreate()

        val EXAMPLE_COUNTER = stringPreferencesKey("token")
        val exampleCounterFlow: Flow<String?> = applicationContext.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[EXAMPLE_COUNTER]
            }

    }

}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "account")