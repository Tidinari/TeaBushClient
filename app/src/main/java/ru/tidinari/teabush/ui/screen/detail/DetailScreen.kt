package ru.tidinari.teabush.ui.screen.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.model.User

@Composable
fun DetailScreen(
    navigationController: NavHostController,
    tea: Tea
) {
    Stopwatch(Modifier.requiredSize(100.dp))
}

@Composable
fun Stopwatch(modifier: Modifier) {
    var startTime by remember { mutableStateOf(System.currentTimeMillis()) }
    var isStopwatchEnabled by remember { mutableStateOf(false) }
    var seconds by remember { mutableStateOf(0) }
    val coroutine = rememberCoroutineScope()

    Surface(
        color = MaterialTheme.colorScheme.primary.copy(0.3f),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = String.format("%02d", seconds),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 6.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            FilledIconToggleButton(onCheckedChange = { checked ->
                startTime = System.currentTimeMillis()
                isStopwatchEnabled = checked
                if (checked) {
                    coroutine.launch {
                        while (isStopwatchEnabled) {
                            seconds++
                            delay(1000)
                        }
                    }
                }
            }, checked = isStopwatchEnabled,
                modifier = Modifier.padding(bottom = 6.dp)) {
                if (isStopwatchEnabled) {
                    Icon(Icons.Filled.Done, null)
                } else {
                    Icon(Icons.Filled.PlayArrow, null)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailPreview() {
    DetailScreen(
        rememberNavController(),
        Tea(
            1,
            "Название",
            "Описание",
            "Рецепт",
            null,
            User("Автор"),
            arrayOf(Tag(1, "Травяной")),
            false,
            3.5f
        )
    )
}