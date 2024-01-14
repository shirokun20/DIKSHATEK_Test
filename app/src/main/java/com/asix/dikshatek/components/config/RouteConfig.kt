package com.asix.dikshatek.components.config

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.asix.dikshatek.components.constants.RouteConst
import com.asix.dikshatek.features.movies.view.genres.GenresView
import com.asix.dikshatek.features.movies.view.movieDetail.MovieDetailView
import com.asix.dikshatek.features.movies.view.moviesByGenre.MovieByGenreView

@Composable
fun RouteConfig() {
    val nav = NavConfig.getNavController()
    NavHost(
        navController = nav, startDestination = RouteConst.genresScreen
    ) {
        composable(route = RouteConst.genresScreen) {
            GenresView()
        }

        composable(
            route = "${RouteConst.movieByGenreScreen}/{genre_id}/{genre_name}",
            arguments = listOf(
                navArgument("genre_id") { type = NavType.StringType },
                navArgument("genre_name") { type = NavType.StringType }
            ),
        ) { entry ->
            MovieByGenreView(entry)
        }

        composable(
            route = "${RouteConst.movieDetailScreen}/{id}/{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
            ),
        ) { entry ->
            MovieDetailView(entry)
        }
    }
}
