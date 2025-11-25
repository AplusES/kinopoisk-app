package com.example.skillcinema.data.repository

import com.example.skillcinema.R
import com.example.skillcinema.data.models.OnboardingItem

/**
 * Вводный экран (онбординг)
 */
object OnboardingList {
    val appName = "Skillcinema"
    val items = listOf(
        OnboardingItem(0, R.string.onboarding_learn, R.drawable.onboarding_learn),
        OnboardingItem(1, R.string.onboarding_create, R.drawable.onboarding_create),
        OnboardingItem(2, R.string.onboarding_share, R.drawable.onboarding_share)
    )
}