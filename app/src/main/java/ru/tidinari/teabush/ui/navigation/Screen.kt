package ru.tidinari.teabush.ui.navigation

import com.google.gson.Gson
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.ui.navigation.graph.DETAIL_TEA

/**
 * Определяет ссылки для навигации
 *
 * @property route путь для навигации
 * @constructor путь для навигации
 */
sealed class Screen(val route: String) {
    /**
     * Экран "Обзор"
     */
    object Overview: Screen(route = "overview")
    /**
     * Экран "Детали", в который нужно прокинуть чай с помощью метода [passTea]
     * @see passTea - метод, который нужно использовать для получения ссылки для навигации
     */
    object Detail: Screen(route = "detail?$DETAIL_TEA={$DETAIL_TEA}") {
        fun passTea(
            tea: Tea,
        ): String {
            return "detail?$DETAIL_TEA=${Gson().toJson(tea)}"
        }
    }

    /**
     * Экран авторизации
     */
    object Auth: Screen(route = "auth")

    /**
     * Экран профиля
     */
    object Profile: Screen(route = "profile")
}
