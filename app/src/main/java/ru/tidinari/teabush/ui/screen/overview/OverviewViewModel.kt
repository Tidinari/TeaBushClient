package ru.tidinari.teabush.ui.screen.overview

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import ru.tidinari.teabush.TeabushApp
import ru.tidinari.teabush.api.TeabushAPI
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.model.User

/**
 * ViewModel обзор
 */
class OverviewViewModel: ViewModel() {

    /**
     * Обновляемый список тэгов
     */
    val tags = mutableStateListOf<Tag>()

    /**
     * Обновляемый список чая
     */
    val teaList = mutableStateListOf<Tea>()

    /**
     * Обновляемый селектор тэгов
     */
    val selectedTags = mutableStateListOf<Tag>()

    /**
     * Получает список тэгов от сервера и обновляет переменную [tags]
     * @see tags используется для каллбэка
     */
    suspend fun getTags() {
        // TODO: Remove fake repo
        delay(1000)
        tags.add(Tag(1, "Травяной"))
        tags.add(Tag(2, "Черный"))
//        if (tags.isEmpty()) {
//            tags.addAll(TeabushApp.apiService.getTags())
//        }
    }

    /**
     * Обновляет переменную [selectedTags]
     *
     * @param tag тэг, который нужно обновить
     * @param select обновить или нет
     */
    fun onTagClicked(tag: Tag, select: Boolean) {
        if (select)
            selectedTags.add(tag)
        else
            selectedTags.remove(tag)
    }

    /**
     * Получает список чая от сервера и обновляет переменную [teaList]
     * @see teaList используется для каллбэка
     *
     * @param page для указания страницы
     */
    suspend fun getTeaList(page: Int) {
        // TODO: Remove fake repo
        delay(1000)
        teaList.addAll(
            listOf(
                Tea(1, "Название", "Описание", "Рецепт",
                    null, User("Автор"), arrayOf(Tag(1, "Травяной")),
                    false, 5f
                ),
                Tea(2, "Название второго чая",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ultricies mi porttitor scelerisque aliquam. Phasellus at sodales neque. Aliquam purus augue, commodo vel consequat sit amet, facilisis ac libero. Sed aliquam, diam eu ultrices convallis, nibh ligula porttitor ipsum, vel aliquet metus ligula sed nisi. Integer vitae justo vitae odio efficitur varius. Aliquam erat volutpat. Quisque eu mauris eu dolor semper placerat. Donec lacinia consequat orci at dignissim. Morbi diam augue, tempus feugiat libero sit amet, bibendum sagittis felis. Integer lorem sem, porta a neque nec, faucibus lacinia orci. Morbi tristique tempus tellus ac suscipit. Vivamus nulla turpis, dignissim sit amet viverra vel, efficitur eu leo. Morbi bibendum ullamcorper orci, et luctus tortor lacinia in. Curabitur id ornare enim. Etiam non facilisis tortor.",
                    "Рецепт", null, User("Тимофей Савкив"), arrayOf(Tag(2, "Черный")),
                    false, 3f
                ),
                Tea(3, "Название третьего чая",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ultricies mi porttitor scelerisque aliquam. Phasellus at sodales neque. Aliquam purus augue, commodo vel consequat sit amet, facilisis ac libero. Sed aliquam, diam eu ultrices convallis, nibh ligula porttitor ipsum, vel aliquet metus ligula sed nisi. Integer vitae justo vitae odio efficitur varius. Aliquam erat volutpat. Quisque eu mauris eu dolor semper placerat. Donec lacinia consequat orci at dignissim. Morbi diam augue, tempus feugiat libero sit amet, bibendum sagittis felis. Integer lorem sem, porta a neque nec, faucibus lacinia orci. Morbi tristique tempus tellus ac suscipit. Vivamus nulla turpis, dignissim sit amet viverra vel, efficitur eu leo. Morbi bibendum ullamcorper orci, et luctus tortor lacinia in. Curabitur id ornare enim. Etiam non facilisis tortor.",
                    "Рецепт", null, User("Тимофей Савкив"), arrayOf(Tag(2, "Черный")),
                    false, 2.5f
                ),
                Tea(4, "Название", "Описание", "Рецепт",
                    null, User("Автор"), arrayOf(Tag(1, "Травяной")),
                    false, 1.5f
                ),
                Tea(5, "Название второго чая",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ultricies mi porttitor scelerisque aliquam. Phasellus at sodales neque. Aliquam purus augue, commodo vel consequat sit amet, facilisis ac libero. Sed aliquam, diam eu ultrices convallis, nibh ligula porttitor ipsum, vel aliquet metus ligula sed nisi. Integer vitae justo vitae odio efficitur varius. Aliquam erat volutpat. Quisque eu mauris eu dolor semper placerat. Donec lacinia consequat orci at dignissim. Morbi diam augue, tempus feugiat libero sit amet, bibendum sagittis felis. Integer lorem sem, porta a neque nec, faucibus lacinia orci. Morbi tristique tempus tellus ac suscipit. Vivamus nulla turpis, dignissim sit amet viverra vel, efficitur eu leo. Morbi bibendum ullamcorper orci, et luctus tortor lacinia in. Curabitur id ornare enim. Etiam non facilisis tortor.",
                    "Рецепт", null, User("Тимофей Савкив"), arrayOf(Tag(2, "Черный")),
                    false, 5f
                ),
                Tea(6, "Название третьего чая",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ultricies mi porttitor scelerisque aliquam. Phasellus at sodales neque. Aliquam purus augue, commodo vel consequat sit amet, facilisis ac libero. Sed aliquam, diam eu ultrices convallis, nibh ligula porttitor ipsum, vel aliquet metus ligula sed nisi. Integer vitae justo vitae odio efficitur varius. Aliquam erat volutpat. Quisque eu mauris eu dolor semper placerat. Donec lacinia consequat orci at dignissim. Morbi diam augue, tempus feugiat libero sit amet, bibendum sagittis felis. Integer lorem sem, porta a neque nec, faucibus lacinia orci. Morbi tristique tempus tellus ac suscipit. Vivamus nulla turpis, dignissim sit amet viverra vel, efficitur eu leo. Morbi bibendum ullamcorper orci, et luctus tortor lacinia in. Curabitur id ornare enim. Etiam non facilisis tortor.",
                    "Рецепт", null, User("Тимофей Савкив"), arrayOf(Tag(1, "Травяной"), Tag(2, "Черный")),
                    false, 4.5f
                )
            )
        )
//        with(TeabushApp.apiService.getTeaList(page)) {
//            teaList.clear()
//            teaList.addAll(this)
//        }
    }
//
//    suspend fun getTeaList(page: Int, search: String) {
//        with(TeabushApp.apiService.getTeaList(page, search)) {
//            teaList.clear()
//            teaList.addAll(this)
//        }
//    }
//
//    suspend fun getTeaList(page: Int, tags: List<Tag>) {
//        with(TeabushApp.apiService.getTeaList(page, tags.map { it.idTag })) {
//            teaList.clear()
//            teaList.addAll(this)
//        }
//    }
//
//    suspend fun getTeaList(page: Int, tags: List<Tag>, search: String) {
//        with(TeabushApp.apiService.getTeaList(page, tags.map { it.idTag }, search)) {
//            teaList.clear()
//            teaList.addAll(this)
//        }
//    }
}