package com.example.skillcinema.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.skillcinema.data.repository.OnboardingList
import com.example.skillcinema.ui.view.LoadingScreen
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

/**
 * Основной экран онбординга, отображающий горизонтальный слайдер с обучающими экранами.
 *
 * @param onNavigateToMain Callback, вызываемый при завершении онбординга или пропуске
 */
@Composable
fun OnboardingScreens(
    onNavigateToMain: () -> Unit
) {

    var isLoading by remember {mutableStateOf(false)}

    if(isLoading){
        LoadingScreen(onLoadingFinished = onNavigateToMain)
    } else{
        val pages = OnboardingList.items
        val pagerState = rememberPagerState(pageCount = { pages.size })
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            ) { pageIndex ->
                OnboardingPageContent(
                    item = pages[pageIndex],
                    onSkipClicked = {
                        isLoading = true
                    }
                )
            }
            DotsIndicator(
                totalPages = pages.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 48.dp)
            )
            LaunchedEffect(pagerState.currentPage) {
                val currentPage = pagerState.currentPage
                if (currentPage == pages.size - 1) {
                    val nextSwipe = snapshotFlow { pagerState.currentPage }
                        .filter { it != currentPage }
                        .first()
                    if (nextSwipe == currentPage) {
                        isLoading = true
                    }
                }
            }
        }
    }





}

/**
 * Индикатор точек, отображающий текущую страницу в горизонтальном пейджере.
 *
 * @param totalPages Общее количество страниц
 * @param currentPage Индекс текущей страницы (начинается с 0)
 * @param modifier Модификатор для настройки внешнего вида индикатора
 */
@Composable
fun DotsIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalPages){ page ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(
                        if(page == currentPage) MaterialTheme.colors.primary
                        else MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                    )
            )
        }
    }

}