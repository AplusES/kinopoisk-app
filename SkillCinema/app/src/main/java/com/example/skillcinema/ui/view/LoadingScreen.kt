package com.example.skillcinema.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillcinema.R
import kotlinx.coroutines.delay

/**
 * Экран загрузки, отображаемый при запуске приложения.
 * Показывает индикатор загрузки и логотип приложения.
 *
 * @param onLoadingFinished Callback, вызываемый по завершении анимации загрузки
 */
@Composable
fun LoadingScreen(
    onLoadingFinished: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(isLoading) {
        if(isLoading) {
            delay(1000)
            isLoading = false
            onLoadingFinished()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(
        vertical = dimensionResource(R.dimen.padding_16dp),
        horizontal = dimensionResource(R.dimen.padding_8dp)
    )){
        Text(
            text = stringResource(R.string.name_application),
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.text_size_large).value.sp
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
        )
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary
        )
        Image(
            painter = painterResource(id = R.drawable.onboarding_learn),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}
