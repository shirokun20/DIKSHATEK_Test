package com.asix.dikshatek.features.movies.view.genres

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.asix.dikshatek.components.widget.StatusBarColorWidget
import com.asix.dikshatek.features.movies.view.genres.child.GenresChildWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenresView() {
    StatusBarColorWidget(Color(0xFF961E1E))
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Genres", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFC62828)),
            )
        }
    ) { innerPadding ->
        GenresChildWidget(innerPadding = innerPadding)
    }
}