package ru.tidinari.teabush.ui.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OverviewTopBar(
    navigateBack: () -> Unit
) {

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun OverviewTopBar(
    onValueChange: (String) -> Unit,
    navigateToProfile: () -> Unit
) {
    var isSearching by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    CenterAlignedTopAppBar(
        title = {
            if (isSearching) {
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it; onValueChange(it) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusRequester.freeFocus()
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .focusRequester(focusRequester)
                )
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
fun TopWithBack() {
    OverviewTopBar {
        {}
    }
}

@Preview
@Composable
fun TopWitoutBack() {
    OverviewTopBar({}, {})
}