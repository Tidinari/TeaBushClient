package ru.tidinari.teabush.ui.screen.detail

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.MoreVert
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
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
import ru.tidinari.teabush.ui.shared.TagItems

/**
 * Отрисовка экрана с деталями конкретного чая
 *
 * @param navigationController навигация для перехода обратно
 * @param tea чай, детали которого будут отображены
 * @param detailViewModel ViewModel для добавления в избранное, оценивания чая
 */
@Composable
fun DetailScreen(
    navigationController: NavHostController,
    tea: Tea,
    detailViewModel: DetailViewModel = viewModel()
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DetailTopBar(navigateBack = {
            navigationController.navigate(Screen.Overview.route)
        }, tea = tea, onClickedFavorite = {
            if (it) {
                detailViewModel.addToFavorite(tea)
            } else {
                detailViewModel.removeFromFavorite(tea)
            }
        })
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            if (tea.image != null) {
                Image(bitmap =  BitmapFactory.decodeByteArray(tea.image, 0, tea.image.size).asImageBitmap(), contentDescription = null)
            }
            Stopwatch()

        }
        DetailSection(nameOfTheSection = "Описание", sectionText = tea.description)
        DetailSection(nameOfTheSection = "Рецепт", sectionText = tea.recipe)
    }
}

@Composable
fun DetailSection(nameOfTheSection: String, sectionText: String) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(4.dp)
            .background(Color.DarkGray)
            .padding(1.dp)
    )
    Text(text = nameOfTheSection, style = MaterialTheme.typography.labelMedium)
    Text(
        text = sectionText,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Left,
        modifier = Modifier.fillMaxWidth(0.9f)
    )
}

/**
 * Отрисовка секундомера
 *
 * @param modifier параметры отрисовки
 */
@Composable
fun Stopwatch(modifier: Modifier = Modifier) {
    var isStopwatchEnabled by remember { mutableStateOf(false) }
    var seconds by remember { mutableStateOf(0) }
    val coroutine = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = MaterialTheme.colorScheme.primary.copy(0.3f),
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
        ) {
            Text(
                text = String.format("%02d", seconds),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
            )
        }
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