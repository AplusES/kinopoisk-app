package com.example.skillcinema.data.models

/**
 * Модель ответа API, содержащая список фильмов с пагинацией.
 * Используется для парсинга ответов от API Кинопоиска.
 *
 * @property total Общее количество фильмов, соответствующих запросу
 * @property totalPages Общее количество страниц с результатами
 * @property items Список фильмов на текущей странице
 */
data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>
)
