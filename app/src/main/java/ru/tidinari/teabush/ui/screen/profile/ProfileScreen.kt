package ru.tidinari.teabush.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(
    navigationController: NavHostController
) {

}

@Preview
@Composable
fun ProfilePreview() {
    ProfileScreen(rememberNavController())
}