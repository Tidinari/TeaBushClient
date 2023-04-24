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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.model.User
import ru.tidinari.teabush.ui.navigation.Screen
import ru.tidinari.teabush.ui.shared.singletonViewModel

@Composable
fun DetailScreen(
    navigationController: NavHostController,
    tea: Tea,
    detailViewModel: DetailViewModel = viewModel()
) {
    Column {
        DetailTopBar(navigateBack = {
            navigationController.navigate(Screen.Overview.route)
        }, tea = tea, onClickedFavorite = {
            if (it) {
                detailViewModel.addToFavorite(tea)
            } else {
                detailViewModel.removeFromFavorite(tea)
            }
        })
        Stopwatch(Modifier.requiredSize(100.dp))
    }
}

@Composable
fun Stopwatch(modifier: Modifier) {
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
                isStopwatchEnabled = checked
                if (checked) {
                    coroutine.launch {
                        while (isStopwatchEnabled) {
                            seconds++
                            delay(1000)
                        }
                    }
                } else {
                    seconds = 0
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    navigateBack: () -> Unit,
    onClickedFavorite: (Boolean) -> Unit,
    tea: Tea
) {
    var isFavorite by remember { mutableStateOf(tea.isFavorite) }
    LargeTopAppBar(
        title = {
            Text(text = tea.name)
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            FilledIconToggleButton(checked = isFavorite, onCheckedChange = {
                onClickedFavorite(it)
                isFavorite = !isFavorite
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        },
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
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