package com.example.skillcinema.ui.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.skillcinema.R
import com.example.skillcinema.data.models.Country
import com.example.skillcinema.data.models.Genre

import com.example.skillcinema.data.models.MovieDetails
import com.example.skillcinema.data.models.MovieListType
import com.example.skillcinema.data.network.KinopoiskApi
import com.example.skillcinema.ui.theme.SkillCinemaTheme
import com.example.skillcinema.ui.viewmodels.MainViewModel

/**
 * Горизонтальный список фильмов с кнопкой "Далее".
 *
 * @param movies Список фильмов для отображения
 * @param modifier Модификатор для настройки внешнего вида списка
 * @param type Тип списка фильмов
 * @param onButtonClick Callback, вызываемый при нажатии на кнопку "Далее"
 */
@Composable
fun HorizontalMovieList(
    movies: List<MovieDetails>,
    modifier: Modifier = Modifier,
    type: MovieListType,
    onButtonClick: (MovieListType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = type.title,
            style = MaterialTheme.typography.headlineMedium,
        )

        Text(
            text = "Все",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onButtonClick(type) },

            )
    }

    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(movies.take(20).size) { index ->
            MovieItem(
                movie = movies[index],
                modifier = Modifier.padding(start = 0.dp, end = 8.dp)
            )
        }
        item {
            ShowAllButton(
                onClick = { onButtonClick(type) }
            )
        }
    }

}

/**
 * Вертикальный список фильмов в виде сетки 2 колонки.
 *
 * @param viewModel ViewModel для получения данных о фильмах
 * @param onBackClick Callback, вызываемый при нажатии кнопки назад
 */
@Composable
fun VerticalMovieList(
    type: MovieListType,
    viewModel: MainViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val movieState by viewModel.movieLists.collectAsState()
    val movies =
        movieState[type]?.movies ?: emptyList()
    Log.d("CheckSizeList", "${movies.size}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            type.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies.size) { movieIndex ->
                MovieItem(
                    movie = movies[movieIndex],
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }


}

/**
 * Элемент списка фильмов, отображающий постер и рейтинг.
 *
 * @param movie Данные о фильме для отображения
 * @param modifier Модификатор для настройки внешнего вида элемента
 */
@Composable
fun MovieItem(
    movie: MovieDetails,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(194.dp)
            .width(111.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(154.dp)
            ) {

                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = movie.nameRu,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .padding(4.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .align(Alignment.TopEnd)
                ) {
                    Log.d("KinopoiskRating", "${movie.ratingKinopoisk}")
                    Text(
                        text = "%.1f".format(movie.ratingKinopoisk ?: 0.0),
                        color = Color.White,
                        fontSize = 8.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = movie.nameRu,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = movie.genres.first().genre,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun ShowAllButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Показать все",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp),

                )
        }
        Text(
            text = "Показать все",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun PreviewButton() {
    SkillCinemaTheme {
        ShowAllButton(
            {}
        )
    }

}


private fun createTestMovie(): MovieDetails {
    return MovieDetails(
        kinopoiskId = 1,
        imdbId = "tt1234567",
        nameRu = "Пример фильма",
        nameEn = "Example Movie",
        nameOriginal = "Example Movie",
        posterUrl = "",
        posterUrlPreview = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg",
        coverUrl = "https://example.com/cover.jpg",
        logoUrl = "https://example.com/logo.png",
        reviewsCount = 42,
        ratingGoodReview = 85.5,
        ratingGoodReviewVoteCount = 100,
        ratingKinopoisk = 8.5,
        ratingKinopoiskVoteCount = 1000,
        ratingImdb = 8.2,
        ratingImdbVoteCount = 50000,
        ratingFilmCritics = 7.8,
        ratingFilmCriticsVoteCount = 50,
        ratingAwait = 95.0,
        ratingAwaitCount = 200,
        ratingRfCritics = 8.0,
        ratingRfCriticsVoteCount = 30,
        webUrl = "https://www.kinopoisk.ru/film/1/",
        year = 2023,
        filmLength = 148,
        slogan = "Пример слогана",
        description = "Пример описания фильма",
        shortDescription = "Краткое описание",
        editorAnnotation = "Примечание редакции",
        isTicketsAvailable = true,
        productionStatus = "POST_PRODUCTION",
        type = "FILM",
        ratingMpaa = "pg13",
        ratingAgeLimits = "16+",
        hasImax = false,
        has3D = false,
        lastSync = "2023-01-01T00:00:00.000Z",
        countries = listOf(Country("Россия")),
        genres = listOf(Genre(genre = "драма"), Genre(genre = "триллер")),
        startYear = 2022,
        endYear = 2023,
        serial = false,
        shortFilm = false,
        completed = true,
        kinopoiskHDId = ""
    )
}

@Preview
@Composable
fun PreviewItem() {
    SkillCinemaTheme {
        Surface {
            MovieItem(
                movie = createTestMovie(),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}