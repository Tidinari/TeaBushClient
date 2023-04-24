package ru.tidinari.teabush.ui.screen.overview

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import ru.tidinari.teabush.TeabushApp
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea

class OverviewViewModel: ViewModel() {

    val tags = mutableStateListOf<Tag>()
    val teaList = mutableStateListOf<Tea>()
    val upperBound = mutableSetOf(Int.MAX_VALUE)

    val selectedTags = mutableStateListOf<Tag>()

    suspend fun getTags() {
        // TODO: Remove fake repo
        delay(1000)
        tags.add(Tag(1, "Травяной"))
        tags.add(Tag(2, "Черный"))
//        if (tags.isEmpty()) {
//            tags.addAll(TeabushApp.apiService.getTags())
//        }
    }

    suspend fun getUpperbound()

    fun onTagClicked(tag: Tag, select: Boolean) {
        if (select)
            selectedTags.add(tag)
        else
            selectedTags.remove(tag)
    }

    suspend fun getTeaList(page: Int) {
        with(TeabushApp.apiService.getTeaList(page)) {
            teaList.clear()
            teaList.addAll(this)
        }
    }

    suspend fun getTeaList(page: Int, search: String) {
        with(TeabushApp.apiService.getTeaList(page, search)) {
            teaList.clear()
            teaList.addAll(this)
        }
    }

    suspend fun getTeaList(page: Int, tags: List<Tag>) {
        with(TeabushApp.apiService.getTeaList(page, tags.map { it.idTag })) {
            teaList.clear()
            teaList.addAll(this)
        }
    }

    suspend fun getTeaList(page: Int, tags: List<Tag>, search: String) {
        with(TeabushApp.apiService.getTeaList(page, tags.map { it.idTag }, search)) {
            teaList.clear()
            teaList.addAll(this)
        }
    }
}