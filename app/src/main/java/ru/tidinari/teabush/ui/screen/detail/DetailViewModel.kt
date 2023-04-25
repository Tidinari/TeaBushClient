package ru.tidinari.teabush.ui.screen.detail

import androidx.annotation.IntRange
import androidx.lifecycle.ViewModel
import ru.tidinari.teabush.data.model.Tea

class DetailViewModel: ViewModel() {

    /**
     * Добавляет чай в избранное. Делает API запрос.
     *
     * @param tea чай для добавления в избранное.
     */
    fun addToFavorite(tea: Tea) {
        // TODO: later add call to api
    }

    /**
     * Удаляет чай из избранного. Делает API запрос.
     *
     * @param tea чай для удаления из избранного.
     */
    fun removeFromFavorite(tea: Tea) {
        // TODO: later add call to api
    }

    /**
     * Делает запрос к API, оценивает конкретный чай.
     *
     * @param tea чай для оценки.
     * @param rating поставленный рейтинг.
     */
    fun rate(tea: Tea, @IntRange(from = 0, to = 5) rating: Int) {
        // TODO: later add call to api
    }
}