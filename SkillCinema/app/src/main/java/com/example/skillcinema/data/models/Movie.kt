package com.example.skillcinema.data.models

/**
 * Основная модель данных, представляющая фильм.
 * Содержит базовую информацию о фильме, такую как название, рейтинг, год выпуска и т.д.
 *
 * @property kinopoiskId Уникальный идентификатор фильма в Кинопоиске
 * @property nameRu Название фильма на русском языке (может быть null)
 * @property nameEn Название фильма на английском языке (может быть null)
 * @property nameOriginal Оригинальное название фильма (может быть null)
 * @property countries Список стран производства фильма
 * @property genres Список жанров фильма
 * @property ratingKinopoisk Рейтинг фильма на Кинопоиске (может быть null)
 * @property ratingImdb Рейтинг фильма на IMDB (может быть null)
 * @property year Год выхода фильма (может быть null)
 * @property type Тип кинопроизведения (например, "FILM", "TV_SERIES" и т.д.)
 * @property posterUrl URL постера фильма (может быть null)
 * @property posterUrlPreview URL уменьшенной версии постера (может быть null)
 * @property duration Продолжительность фильма в минутах (может быть null)
 * @property premiereRu Дата премьеры в России в формате строки (может быть null)
 */
data class Movie(
    val kinopoiskId: Int,
    val nameRu: String? = null,
    val nameEn: String? = null,
    val nameOriginal: String? = null,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double? = null,
    val ratingImdb: Double? = null,
    val year: Int? = null,
    val type: String? = null,
    val posterUrl: String? = null,
    val posterUrlPreview: String? = null,
    val duration: Int? = null,
    val premiereRu: String? = null
)


