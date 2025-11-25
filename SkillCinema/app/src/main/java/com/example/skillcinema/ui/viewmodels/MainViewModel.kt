package com.example.skillcinema.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillcinema.data.models.Month
import com.example.skillcinema.data.models.Movie
import com.example.skillcinema.data.models.MovieListState
import com.example.skillcinema.data.models.MovieListType
import com.example.skillcinema.data.network.KinopoiskApi
import com.example.skillcinema.data.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val api: KinopoiskApi = RetrofitClient.kinopoiskApi

    private val _movieLists = MutableStateFlow<Map<MovieListType, MovieListState>>(
        MovieListType.entries.associateWith { type ->
            MovieListState(type = type)
        }
    )
    val movieLists = _movieLists.asStateFlow()

    fun loadMovieList(type: MovieListType, isRefresh: Boolean) {

            val currentState = _movieLists.value[type] ?: return
            if(currentState.isLoading || !currentState.hasMorePages) return

            _movieLists.update { currentMap ->
                currentMap.toMutableMap().apply {
                    this[type] = this[type]?.copy(isLoading = true) ?: return
                }
            }
            viewModelScope.launch {
                try {

                    val currentState = _movieLists.value[type] ?: return@launch
                    val nextPage = if(isRefresh) 1 else currentState.currentPage

                    val calendar = Calendar.getInstance()
                    val currentYear = calendar.get(Calendar.YEAR)
                    val currentMonth = Month.items[calendar.get(Calendar.MONTH)]
                    val response = when (type) {
                        MovieListType.PREMIERES -> api.getPremieres(
                            page = nextPage,
                            month = currentMonth,
                            year = currentYear
                        )
                    }

                    val movieDetailsDeferred = response.items.map { movie ->
                        async{
                            try{
                                api.getMovie(movie.kinopoiskId)
                            } catch (e: Exception){
                                null
                            }
                        }
                    }

                    val movieDetails = movieDetailsDeferred.awaitAll().filterNotNull()

                    _movieLists.update { currentMap ->
                        currentMap.toMutableMap().apply {
                            val currentList = if (isRefresh) emptyList() else currentState.movies
                            this[type] = this[type]?.copy(
                                movies = currentList + movieDetails,
                                isLoading = false,
                                currentPage = nextPage + 1,
                                hasMorePages = response.items.isNotEmpty()
                            ) ?: return@launch
                        }
                    }
                } catch (e: Exception) {
                    Log.d("TryCheck", "3")
                    _movieLists.update { currentMap ->
                        currentMap.toMutableMap().apply {
                            this[type] = this[type]?.copy(
                                isLoading = false,
                                error = e.message ?: "Ошибка загрузки"
                            ) ?: return@launch
                        }
                    }
                }
            }

        }

}