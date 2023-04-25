package ru.tidinari.teabush.data.server

/**
 * Ответ сервера на запрос, относящийся к авторизации
 *
 * @property message ответ от сервера
 */
data class AuthResponse(
    val message: String
)