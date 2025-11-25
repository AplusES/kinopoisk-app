package com.example.skillcinema.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ModalDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.skillcinema.R
import com.example.skillcinema.data.models.MovieListType
import com.example.skillcinema.ui.viewmodels.MainViewModel
import java.nio.file.WatchEvent

/**
 * Главный экран приложения, отображающий список фильмов.
 *
 * @param navController Контроллер навигации для перехода между экранами
 * @param viewModel ViewModel для управления состоянием экрана
 */
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val movieState by viewModel.movieLists.collectAsState()
    val premieres = movieState[MovieListType.PREMIERES]?.movies ?: emptyList()

    val error = movieState[MovieListType.PREMIERES]?.error
    val isLoading = movieState[MovieListType.PREMIERES]?.isLoading ?: false

    LaunchedEffect(Unit) {
        if (premieres.isEmpty()) {
            viewModel.loadMovieList(MovieListType.PREMIERES, true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(R.dimen.horizontal_main_padding),
                horizontal = dimensionResource(R.dimen.vertical_main_padding)
            )
    ) {
        Text(
            text = stringResource(R.string.name_application),
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.text_size_large).value.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        if (premieres.isNotEmpty()) {
            Log.d("LoadCheck", "1.1")
            HorizontalMovieList(
                movies = premieres,
                modifier = Modifier.fillMaxWidth(),
                type = MovieListType.PREMIERES,

                ) { listType ->
                navController.navigate("verticalMovieList/${listType.name}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMain() {
//    MainScreen()
}