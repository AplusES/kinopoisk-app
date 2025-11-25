package com.example.skillcinema.data.models

/**
 * Перечисление типов списков фильмов в приложении.
 * Используется для идентификации и управления различными категориями фильмов.
 *
 * @property title Отображаемое название категории фильмов
 */
enum class MovieListType(val title: String) {
    /** Список премьерных фильмов */
    PREMIERES("Премьеры"),
}