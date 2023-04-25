package ru.tidinari.teabush

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ru.tidinari.teabush.ui.navigation.graph.SetupNavGraph
import ru.tidinari.teabush.ui.theme.TeaBushCTheme

/**
 * Входная точка приложения, единственная Activity.
 *
 * @constructor Android'ом создаётся компонент приложения
 */
class MainActivity : ComponentActivity() {
    /**
     * При наступлении onCreate создаёт NavigationController,
     * используя Jetpack Compose
     *
     * @param savedInstanceState сохранённое состояние приложения
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeaBushCTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}