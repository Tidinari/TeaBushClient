package ru.tidinari.teabush.data.model


/**
 * Чай
 *
 * @property id идентификатор
 * @property name название чая
 * @property description описание чая
 * @property recipe рецепт
 * @property image картинка чая
 * @property author автор
 * @property tags тэги
 * @property isFavorite избранное или нет
 * @property averageRating средний рейтинг чая
 */
data class Tea(
    val id: Int,
    val name: String,
    val description: String,
    val recipe: String,
    val image: ByteArray?,
    val author: User,
    val tags: Array<Tag>,
    val isFavorite: Boolean,
    val averageRating: Float
) {
    /**
     * Проверяет на идентичность ID и название чая
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tea

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }
}
