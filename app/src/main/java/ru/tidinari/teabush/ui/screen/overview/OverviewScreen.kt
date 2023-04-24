package ru.tidinari.teabush.ui.screen.overview

import android.graphics.fonts.FontStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.ui.navigation.Screen
import ru.tidinari.teabush.ui.shared.OverviewTopBar
import ru.tidinari.teabush.ui.utils.TeaCard

@Composable
fun OverviewScreen(
    navigationController: NavHostController,
    viewModel: OverviewViewModel
) {
    var page by rememberSaveable { mutableStateOf(0) }
    OverviewTopBar(
        navigateToProfile = {
            navigationController.navigate(Screen.Profile.route)
        },
        onValueChange = {
            page = 0
            // TODO: not implemented yet
            // viewModel.getTeaList(page, it)
        }
    )
    LazyRow(modifier = Modifier.animateContentSize()) {
        items(items = viewModel.tags) { tag ->
            TagCard(tag = tag, onSelected = { selected ->
                viewModel.onTagClicked(tag, selected)
            })
        }
    }
    LazyColumn {
        items(items = viewModel.teaList) { tea ->
            TeaCard(tea = tea)
        }
        item {
            PageSelector(onPageChanged = {}, upperBound = viewModel.upperBound)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getTags()
    }
}

@Composable
fun TagCard(modifier: Modifier = Modifier, tag: Tag, onSelected: (Boolean) -> Unit) {
    val selected = remember {
        MutableTransitionState(false)
    }
    Button(onClick = {
        selected.targetState = !selected.currentState
        onSelected(selected.targetState)
    }) {
        AnimatedVisibility(visibleState = selected) {
            Icon(Icons.Filled.Check, contentDescription = null, Modifier.padding(end = 4.dp))
        }
        Text(text = tag.name)
    }
}

@Composable
fun PageSelector(modifier: Modifier = Modifier, onPageChanged: (Int) -> Unit, upperBound: Int) {
    var selectedPage by remember { mutableStateOf(1) }
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(
            items = (maxOf(1, selectedPage - 2)..minOf(upperBound, maxOf(5, selectedPage + 2)))
                .asIterable().toList()
        ) { page ->
            PageItem(
                page = page,
                selected = page == selectedPage,
                onSelect = { selectedPage = it; onPageChanged(it) },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageItem(modifier: Modifier = Modifier, page: Int, selected: Boolean, onSelect: (Int) -> Unit) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        if (selected) {
            FilledTonalButton(
                contentPadding = PaddingValues(0.dp),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = { onSelect(page) },
                modifier = modifier.size(36.dp)
            ) {
                Text(text = "$page", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }
        } else {
            OutlinedButton(
                contentPadding = PaddingValues(0.dp),
                shape = MaterialTheme.shapes.small,
                onClick = { onSelect(page) },
                modifier = modifier.size(36.dp)
            ) {
                Text(text = "$page", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview
@Composable
fun OverviewPreview() {
    OverviewScreen(rememberNavController(), OverviewViewModel())
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