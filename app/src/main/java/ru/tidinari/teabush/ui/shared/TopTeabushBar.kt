package ru.tidinari.teabush.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Верхняя полоса для экрана "Обзор"
 *
 * @param onValueChange используется при поиске
 * @param navigateToProfile используется для навигации к профилю пользователя
 * @param onClose используется при закрытии строки поиска
 * @receiver каллбэк для строки поиска
 * @receiver каллбэк для навигации при нажатии кнопки "назад"
 * @receiver каллбэк для реагирования на закрытию строки поиска
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun OverviewTopBar(
    onValueChange: (String) -> Unit,
    navigateToProfile: () -> Unit,
    onClose: () -> Unit
) {
    var isSearching by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    CenterAlignedTopAppBar(
        title = {
            if (isSearching) {
                Column {
                    BasicTextField(
                        value = search,
                        onValueChange = { search = it; onValueChange(it) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                focusRequester.freeFocus()
                                keyboardController?.hide()
                            }
                        ),
                        textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light, textDecoration = TextDecoration.None),
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 1.dp)
                            .focusRequester(focusRequester)
                            .fillMaxWidth(0.8f)
                    )
                    Divider(Modifier.fillMaxWidth())
                }
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            } else {
                Text(text = "Справочник чая")
            }
        },
        actions = {
            IconButton(onClick = {
                isSearching = !isSearching
                if (!isSearching) onClose()
                if (isSearching) onValueChange(search)
            }) {
                Icon(if (isSearching) Icons.Filled.Close else Icons.Filled.Search, contentDescription = null)
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigateToProfile() }) {
                Icon(Icons.Filled.Person, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
fun TopWitoutBack() {
    OverviewTopBar({}, {}, {})
}