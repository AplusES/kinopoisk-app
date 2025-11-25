package com.example.skillcinema.data.models

/**
 * Объект, содержащий список названий месяцев на английском языке.
 * Используется для формирования запросов к API Кинопоиска, где требуется указать месяц.
 */
object Month {
    /**
     * Список названий месяцев в верхнем регистре на английском языке.
     * Индексация начинается с 0 (январь) до 11 (декабрь).
     */
    val items = listOf<String>(
        "JANUARY",
        "FEBRUARY",
        "MARCH",
        "APRIL",
        "MAY",
        "JUNE",
        "JULY",
        "AUGUST",
        "SEPTEMBER",
        "OCTOBER",
        "NOVEMBER",
        "DECEMBER"
    )
}