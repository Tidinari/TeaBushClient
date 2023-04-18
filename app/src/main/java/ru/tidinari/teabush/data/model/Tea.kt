package ru.tidinari.teabush.data.model


//id, название, описание, рецепт, картинку, id автора. (для локальной БД)
// isFavorite, averageRating
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
