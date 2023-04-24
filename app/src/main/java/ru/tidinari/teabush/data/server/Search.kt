package ru.tidinari.teabush.data.server

data class Search(
    val page: Int,
    val tags: List<Int>,
    val query: String
)
