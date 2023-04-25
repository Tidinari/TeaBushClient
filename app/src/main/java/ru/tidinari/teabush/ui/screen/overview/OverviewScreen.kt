package ru.tidinari.teabush.ui.screen.overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.ui.navigation.Screen
import ru.tidinari.teabush.ui.shared.OverviewTopBar
import ru.tidinari.teabush.ui.shared.TeaCard
import java.util.Locale

/**
 * Отрисовка экрана "Обзор"
 *
 * @param navigationController корневой контроллер навигации
 * @param viewModel ViewModel для получения списка чая и тегов
 */
@Composable
fun OverviewScreen(
    navigationController: NavHostController,
    viewModel: OverviewViewModel
) {
    var search by rememberSaveable { mutableStateOf("") }

    Column {
        OverviewTopBar(
            navigateToProfile = {
                navigationController.navigate(Screen.Profile.route)
            },
            onValueChange = {
                search = it
            },
            onClose = {
                search = ""
            }
        )

        Column(Modifier.weight(1f)) {
            LazyRow(
                modifier = Modifier
                    .animateContentSize()
            ) {
                items(items = viewModel.tags) { tag ->
                    TagCard(tag = tag, onSelected = { selected ->
                        viewModel.onTagClicked(tag, selected)
                    })
                }
            }
            LazyColumn(Modifier.animateContentSize()) {
                items(items = viewModel.teaList) { tea ->
                    if (viewModel.selectedTags.all { tea.tags.contains(it) } && (tea.name.startsWith(
                            search
                        ) || tea.description.lowercase(
                            Locale.ROOT
                        ).contains(search.lowercase(Locale.ROOT)))) {
                        TeaCard(tea = tea, onClick = {
                            navigationController.navigate(Screen.Detail.passTea(tea))
                        })
                    }
                }
            }
        }

        // PageSelector(onPageChanged = {}, upperBound = 1, modifier = Modifier.fillMaxWidth())

        LaunchedEffect(Unit) {
            viewModel.getTags()
            viewModel.getTeaList(1)
        }
    }
}

/**
 * Отрисовка тэга
 *
 * @param modifier параметры отрисовки
 * @param tag тэг
 * @param onSelected выбран/не выбран
 * @receiver каллбэк при выборе тэга
 */
@Composable
fun TagCard(modifier: Modifier = Modifier, tag: Tag, onSelected: (Boolean) -> Unit) {
    val selected = remember {
        MutableTransitionState(false)
    }

    val containerColor by animateColorAsState(
        targetValue =
        if (selected.targetState)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.outlineVariant
    )

    val textColor by animateColorAsState(
        targetValue =
        if (selected.targetState)
            MaterialTheme.colorScheme.onPrimaryContainer
        else
            Color.Black.copy(0.6f)
    )


    Surface(
        color = containerColor,
        modifier = Modifier
            .clickable {
                selected.targetState = !selected.targetState
                onSelected(selected.targetState)
            }
            .animateContentSize()
            .padding(4.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row {
            AnimatedVisibility(visibleState = selected) {
                Icon(Icons.Filled.Check, contentDescription = null, Modifier.padding(start = 4.dp, end = 2.dp).requiredSize(28.dp))
            }
            Text(text = tag.name, modifier = Modifier.padding(4.dp),
            color = textColor)
        }
    }
}

/**
 * Выбор страницы
 *
 * @param modifier параметры отрисовки
 * @param onPageChanged каллбэк, вызываемый при выборе странице
 * @param upperBound максимальное количество страниц
 * @receiver каллбэк для реагирования на выбор страницы
 */
@Composable
fun PageSelector(modifier: Modifier = Modifier, onPageChanged: (Int) -> Unit, upperBound: Int) {
    var selectedPage by remember { mutableStateOf(1) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        IconButton(onClick = { onPageChanged(selectedPage - 1); selectedPage -= 1 }, enabled = selectedPage > 1) {
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)
        }
        Surface(color = MaterialTheme.colorScheme.primaryContainer) {
            Text(text = "$selectedPage", modifier = Modifier.padding(15.dp))
        }
        IconButton(onClick = { onPageChanged(selectedPage + 1); selectedPage += 1 }, enabled = selectedPage < upperBound) {
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun TagPreview() {
    TagCard(tag = Tag(1, "Привет!"), onSelected = { })
}

@Preview
@Composable
fun PageItemPreview() {
    PageSelector(onPageChanged = {}, upperBound = 14)
}