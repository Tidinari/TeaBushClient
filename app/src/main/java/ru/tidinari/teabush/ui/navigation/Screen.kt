package ru.tidinari.teabush.ui.navigation

import com.google.gson.Gson
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.ui.navigation.graph.DETAIL_TEA

sealed class Screen(val route: String) {
    object Overview: Screen(route = "overview")
    object Detail: Screen(route = "detail?tea={$DETAIL_TEA}") {
        fun passTea(
            tea: Tea,
        ): String {
            return "detail_screen?tea=${Gson().toJson(tea)}"
        }
    }
    object Auth: Screen(route = "auth")
    object Profile: Screen(route = "profile")
}
