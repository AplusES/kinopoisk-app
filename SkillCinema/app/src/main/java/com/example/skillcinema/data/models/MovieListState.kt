package com.example.skillcinema.data.models

/**
 * Модель состояния списка фильмов.
 * Используется для хранения и управления состоянием загрузки и отображения списка фильмов.
 *
 * @property type Тип списка фильмов (премьеры, топ и т.д.)
 * @property movies Список фильмов в текущем состоянии
 * @property isLoading Флаг, указывающий на выполнение загрузки данных
 * @property error Сообщение об ошибке, если загрузка не удалась
 * @property currentPage Текущая страница пагинации
 * @property hasMorePages Флаг, указывающий на наличие дополнительных страниц для загрузки
 */
data class MovieListState(
    val type: MovieListType,
    val movies: List<MovieDetails> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true
)