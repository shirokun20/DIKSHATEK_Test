package com.asix.dikshatek.features.movies.view.movieDetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import com.asix.dikshatek.components.config.NavConfig
import com.asix.dikshatek.components.widget.StatusBarColorWidget
import com.asix.dikshatek.features.movies.view.movieDetail.child.MovieDetailContentChildWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailView(entry: NavBackStackEntry) {
    val id = entry.arguments?.getString("id") ?: ""
    val nav = NavConfig.getNavController()
    StatusBarColorWidget(Color(0xFF961E1E))
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Movie",
                        color = Color.White,
                        maxLines = 2,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFC62828)),
                navigationIcon = {
                    IconButton(onClick = { nav.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MovieDetailContentChildWidget(id = id, innerPadding = innerPadding)
    }
}