package ru.tidinari.teabush.ui.screen.overview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun OverviewScreen(
    navigationController: NavHostController
) {

}

@Preview
@Composable
fun OverviewPreview() {
    OverviewScreen(rememberNavController())
}