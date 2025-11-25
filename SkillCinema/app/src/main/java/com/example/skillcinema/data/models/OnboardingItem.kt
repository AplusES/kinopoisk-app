package com.example.skillcinema.data.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Модель элемента онбординга (вводного экрана).
 * Используется для отображения обучающих экранов при первом запуске приложения.
 *
 * @property id Уникальный идентификатор элемента онбординга
 * @property description Текстовое описание, ссылающееся на строковый ресурс
 * @property image Изображение, ссылающееся на ресурс изображения
 */
data class OnboardingItem(
    val id: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)
