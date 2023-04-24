package ru.tidinari.teabush.data.server

import okhttp3.Interceptor
import okhttp3.Response
import ru.tidinari.teabush.TeabushApp
import java.io.IOException

class BasicAuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", TeabushApp.token).build()
        return chain.proceed(authenticatedRequest)
    }
}