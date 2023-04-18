package ru.tidinari.teabush

import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import ru.tidinari.teabush.data.model.Tag
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.data.model.User

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val tea = Tea(
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
        val serialization = Gson().toJson(tea)
        // Test JSON serialization
        assertEquals(
            "{\"id\":1,\"name\":\"Название\",\"description\":\"Описание\",\"recipe\":\"Рецепт\",\"author\":{\"name\":\"Автор\"},\"tags\":[{\"idTag\":1,\"name\":\"Травяной\"}],\"isFavorite\":false,\"averageRating\":3.5}",
            serialization
        )
        // Test JSON deserialization
        assertEquals(tea, Gson().fromJson(serialization, Tea::class.java))
    }
}