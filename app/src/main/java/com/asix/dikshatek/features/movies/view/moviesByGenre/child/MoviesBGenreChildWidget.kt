package com.asix.dikshatek.features.movies.view.moviesByGenre.child

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asix.dikshatek.components.config.NavConfig
import com.asix.dikshatek.components.constants.RouteConst
import com.asix.dikshatek.components.widget.LoadingWidget
import com.asix.dikshatek.features.movies.model.movieByGenre.MovieModel
import com.asix.dikshatek.features.movies.state.MoviesByGenreState
import com.asix.dikshatek.features.movies.viewModel.moviesVM.MoviesViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesBGenreChildWidget(innerPadding: PaddingValues, id: String) {
    val vm = remember { MoviesViewModel(id) }
    val state: State<MoviesByGenreState> = vm.state
    val isRefreshing by vm.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { vm.onRefresh() })
    val scrollState = rememberScrollState()
    val nav = NavConfig.getNavController()

    LaunchedEffect(Unit) {
        vm.fetchMovies()
    }

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (val currentState = state.value) {
                is MoviesByGenreState.Loading -> {
                    LoadingWidget()
                }

                is MoviesByGenreState.Success -> {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "List Movies",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(
                                    state = scrollState
                                )
                        ) {
                            currentState.data.forEach { res ->
                                MovieItem(res, onClick = {
                                    nav.navigate("${RouteConst.movieDetailScreen}/${res.id}/${res.title}")
                                })
                            }
                            LoadMoreBtn(
                                title = "Click see movies"
                            ) {
                                vm.fetchMovies()
                            }
                        }
                    }
                }

                is MoviesByGenreState.Error -> {

                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            contentColor = Color(0xFF961E1E),
            modifier = Modifier.align(
                alignment = Alignment.TopCenter
            ),
        )
    }
}

@Composable
fun LoadMoreBtn(title: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(
                onClick = onClick
            )
            .padding(15.dp),
        text = title,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 15.sp,
            color = Color(0xFF000000),
        )
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun MovieItem(res: MovieModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color(0xFFEBEEF4)),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        Column {
            Row {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${res.poster_path}",
                    contentDescription = null,
                    modifier = Modifier
                        .width(70.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        if (res.release_date != null) {
                            res.release_date.substring(0, 4)
                        } else {
                            "Unknown"
                        },
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color(0xFFBDBDBD),
                        )
                    )
                    Text(
                        res.title, style = TextStyle(
                            fontSize = 15.sp,
                            color = Color(0xFF000000),
                            fontWeight = FontWeight(weight = 800)
                        )
                    )
                    Text(
                        text =
                        res.overview.ifEmpty {
                            "No Description"
                        },
                        maxLines = 4,
                        fontSize = 15.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Divider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color(0xFFBDBDBD),
                thickness = 1.dp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See detail",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color(0xFF000000),
                    ),
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier
                        .height(15.dp)
                        .width(15.dp),
                    tint = Color.Black
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}