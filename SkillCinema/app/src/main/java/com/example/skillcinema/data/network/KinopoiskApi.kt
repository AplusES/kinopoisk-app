package com.example.skillcinema.data.network

import com.example.skillcinema.data.models.MovieDetails
import com.example.skillcinema.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Интерфейс для работы с API Кинопоиска.
 * Определяет эндпоинты и параметры запросов к API.
 */
interface KinopoiskApi {
    /**
     * Получение списка фильмов с пагинацией.
     *
     * @param page Номер страницы для пагинации
     * @return [MovieResponse] с данными о фильмах и пагинацией
     */
    @GET("/api/v2.2/films")
    suspend fun getMovies(@Query("page") page: Int): MovieResponse

    /**
     * Получение списка премьерных фильмов за указанный месяц и год.
     *
     * @param page Номер страницы для пагинации
     * @param year Год премьер
     * @param month Месяц премьер (например, "JANUARY", "FEBRUARY" и т.д.)
     * @return [MovieResponse] с данными о премьерных фильмах
     */
    @GET("/api/v2.2/films/premieres")
    suspend fun getPremieres(
        @Query("page") page: Int,
        @Query("year") year: Int,
        @Query("month") month: String
    ): MovieResponse

    /**
     * Получение детальной информации о фильме по его идентификатору.
     *
     * @param userId Идентификатор фильма в Кинопоиске
     * @return [MovieDetails] с полной информацией о фильме
     */
    @GET("/api/v2.2/films/{id}")
    suspend fun getMovie(
        @Path("id") userId: Int
    ): MovieDetails

}