package com.example.skillcinema.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.skillcinema.R

/**
 * Экран отображения детальной информации о фильме.
 * В текущей реализации отображает только постер фильма.
 *
 * @see [HorizontalMovieList] для отображения списка фильмов
 * @see [VerticalMovieList] для отображения фильмов в виде сетки
 */
@Composable
fun FilmScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(R.dimen.horizontal_main_padding),
                vertical = dimensionResource(R.dimen.vertical_main_padding)
            )
    ) {
        Box(modifier = Modifier
            .width(360.dp)
            .height(400.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = "https://kinopoiskapiunofficial.tech/images/posters/kp/5051367.jpg",
                placeholder = painterResource(R.drawable.test_poster),
                contentDescription = ""
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    FilmScreen()
}
