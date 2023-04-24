package ru.tidinari.teabush.ui.navigation

import com.google.gson.Gson
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.ui.navigation.graph.DETAIL_TEA

sealed class Screen(val route: String) {
    object Overview: Screen(route = "overview")
    object Detail: Screen(route = "detail?$DETAIL_TEA={$DETAIL_TEA}") {
        fun passTea(
            tea: Tea,
        ): String {
            return "detail?$DETAIL_TEA=${Gson().toJson(tea)}"
        }
    }
    object Auth: Screen(route = "auth")
    object Profile: Screen(route = "profile")
}
