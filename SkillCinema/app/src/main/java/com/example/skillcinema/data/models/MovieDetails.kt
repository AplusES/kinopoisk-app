package com.example.skillcinema.data.models

import com.google.gson.annotations.SerializedName
/**
 * Расширенная модель данных, содержащая полную информацию о фильме.
 * Наследует все поля из [Movie] и добавляет дополнительные детали.
 *
 * @property kinopoiskId Уникальный идентификатор фильма в Кинопоиске
 * @property kinopoiskHDId Идентификатор HD-версии фильма в Кинопоиске
 * @property imdbId Идентификатор фильма в IMDB
 * @property nameRu Название на русском языке
 * @property nameEn Название на английском языке
 * @property nameOriginal Оригинальное название
 * @property posterUrl URL постера фильма
 * @property posterUrlPreview URL уменьшенной версии постера
 * @property coverUrl URL обложки фильма
 * @property logoUrl URL логотипа студии/фильма
 * @property reviewsCount Количество отзывов
 * @property ratingGoodReview Рейтинг положительных отзывов
 * @property ratingGoodReviewVoteCount Количество голосов положительных отзывов
 * @property ratingKinopoisk Рейтинг Кинопоиска
 * @property ratingKinopoiskVoteCount Количество голосов на Кинопоиске
 * @property ratingImdb Рейтинг IMDB
 * @property ratingImdbVoteCount Количество голосов на IMDB
 * @property ratingFilmCritics Рейтинг кинокритиков
 * @property ratingFilmCriticsVoteCount Количество голосов кинокритиков
 * @property ratingAwait Ожидаемый рейтинг
 * @property ratingAwaitCount Количество ожидающих оценок
 * @property ratingRfCritics Рейтинг российских кинокритиков
 * @property ratingRfCriticsVoteCount Количество голосов российских кинокритиков
 * @property webUrl URL страницы фильма на Кинопоиске
 * @property year Год выхода
 * @property filmLength Продолжительность в минутах
 * @property slogan Слоган фильма
 * @property description Полное описание
 * @property shortDescription Краткое описание
 * @property editorAnnotation Примечание редактора
 * @property isTicketsAvailable Доступны ли билеты
 * @property productionStatus Статус производства
 * @property type Тип (фильм, сериал и т.д.)
 * @property ratingMpaa Рейтинг MPAA
 * @property ratingAgeLimits Возрастные ограничения
 * @property hasImax Доступен ли в IMAX
 * @property has3D Доступен ли в 3D
 * @property lastSync Время последнего обновления
 * @property countries Список стран производства
 * @property genres Список жанров
 * @property startYear Год начала показа (для сериалов)
 * @property endYear Год окончания показа (для сериалов)
 * @property serial Является ли сериалом
 * @property shortFilm Является ли короткометражным
 * @property completed Завершен ли (для сериалов)
 */
data class MovieDetails(
    val kinopoiskId: Int,
    val kinopoiskHDId: String,
    val imdbId: String,
    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val coverUrl: String,
    val logoUrl: String,
    val reviewsCount: Int,
    val ratingGoodReview: Double,
    val ratingGoodReviewVoteCount: Int,
    val ratingKinopoisk: Double,
    val ratingKinopoiskVoteCount: Int,
    val ratingImdb: Double,
    val ratingImdbVoteCount: Int,
    val ratingFilmCritics: Double,
    val ratingFilmCriticsVoteCount: Int,
    val ratingAwait: Double,
    val ratingAwaitCount: Int,
    val ratingRfCritics: Double,
    val ratingRfCriticsVoteCount: Int,
    val webUrl: String,
    val year: Int,
    val filmLength: Int,
    val slogan: String,
    val description: String,
    val shortDescription: String,
    val editorAnnotation: String,
    val isTicketsAvailable: Boolean,
    val productionStatus: String,
    val type: String,
    val ratingMpaa: String,
    val ratingAgeLimits: String,
    val hasImax: Boolean,
    val has3D: Boolean,
    val lastSync: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val startYear: Int,
    val endYear: Int,
    val serial: Boolean,
    val shortFilm: Boolean,
    val completed: Boolean
)

/**
 * Модель страны производства фильма
 * @property country Название страны
 */
data class Country(
    val country: String
)

/**
 * Модель жанра фильма
 * @property genre Название жанра
 */
data class Genre(
    val genre: String
)

