package com.example.skillcinema.ui.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.skillcinema.data.models.MovieListType
import com.example.skillcinema.ui.onboarding.OnboardingScreens
import com.example.skillcinema.ui.theme.SkillCinemaTheme
import com.example.skillcinema.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
    }
}


@Composable
fun MainApp(navController: NavHostController = rememberNavController()) {
    SkillCinemaTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            NavHost(
                navController = navController,
                startDestination = "onboarding"
            ) {
                composable("onboarding") {
                    OnboardingScreens(
                        onNavigateToMain = {
                            navController.navigate("main") {
                                popUpTo("onboarding") { inclusive = true }
                            }
                        }
                    )
                }
                composable("main") {
                    val viewModel: MainViewModel = hiltViewModel()
                    MainScreen(navController, viewModel)
                }
                composable(
                    "verticalMovieList/{type}",
                    arguments = listOf(navArgument("type") { type = NavType.StringType})
                ) { backStackEntry ->
                    val typeArg = backStackEntry.arguments?.getString("type")
                    val listType = runCatching { MovieListType.valueOf(typeArg!!) }
                        .getOrElse { MovieListType.PREMIERES } //дефолт на случай ошибки
                    VerticalMovieList(
                        type = listType,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}