package ru.tidinari.teabush.ui.screen.auth

import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {

    /**
     * Регистрирует пользователя с указанным логином и паролем.
     * Испольует хэш SHA256 для прокидывания пароля на сервер
     *
     * @param login логин пользователя
     * @param password пароль пользователя
     */
    fun register(login: String, password: String) {
        // TODO: Implement register thing
    }

    /**
     * Аутентифицирует пользователя.
     * Использует хэш SHA256 для прокидывания пароля на сервер
     *
     * @param login логин пользователя
     * @param password пароль пользователя
     */
    fun login(login: String, password: String) {
        // TODO: Implement login thing
    }
}