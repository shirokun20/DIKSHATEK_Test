package com.asix.dikshatek.features.movies.view.genres.child

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asix.dikshatek.components.config.NavConfig
import com.asix.dikshatek.components.constants.RouteConst
import com.asix.dikshatek.components.widget.LoadingWidget
import com.asix.dikshatek.features.movies.model.genres.GenreModel
import com.asix.dikshatek.features.movies.state.GenresState
import com.asix.dikshatek.features.movies.viewModel.genresVM.GenresViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenresChildWidget(innerPadding: PaddingValues, vm: GenresViewModel = viewModel()) {
    val state: State<GenresState> = vm.state
    val isRefreshing by vm.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { vm.onRefresh() })
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .padding(innerPadding),
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (val currentState = state.value) {
                is GenresState.Loading -> {
                    LoadingWidget()
                }

                is GenresState.Success -> {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "List Genres",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier
                                .verticalScroll(
                                    rememberScrollState()
                                )
                        ) {
                            currentState.data.forEach { res ->
                                GenreItem(res)
                            }
                        }

                    }
                }

                is GenresState.Error -> {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = currentState.errorMessage, style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF000000),
                                )
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(
                alignment = Alignment.TopCenter
            ),
        )
    }
}

@Composable
fun GenreItem(res: GenreModel) {
    val nav = NavConfig.getNavController()
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
            .clickable(onClick = {
                nav.navigate("${RouteConst.movieByGenreScreen}/${res.id}/${res.name}", navOptions = null)
            })
            .padding(15.dp),
    ) {
        Text(
            res.name, style = TextStyle(
                fontSize = 15.sp,
                color = Color(0xFF000000),
            )
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}