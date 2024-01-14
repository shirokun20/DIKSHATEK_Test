package com.asix.dikshatek.features.movies.view.movieDetail.child

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.asix.dikshatek.features.movies.state.MovieDetailState
import com.asix.dikshatek.features.movies.state.ReviewsState
import com.asix.dikshatek.features.movies.state.VideosState
import com.asix.dikshatek.features.movies.viewModel.movieDetailVM.MovieDetailViewModel

@Composable
fun MovieDetailContentChildWidget(innerPadding: PaddingValues, id: String) {
    val vm = remember { MovieDetailViewModel(id) }
    val stateDs: State<MovieDetailState> = vm.stateDs
    val stateVds: State<VideosState> = vm.stateVds
    val stateRvs: State<ReviewsState> = vm.stateRvs
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        vm.fetchAll()
    }

    Box(
        modifier = Modifier
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(
                    state = scrollState
                )
                .fillMaxSize()
        ) {
            when (val currentState = stateDs.value) {
                is MovieDetailState.Loading -> {

                }

                is MovieDetailState.Success -> {
                    MovieDetailCard(currentState.data)
                    Divider(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                        thickness = 1.dp,
                        color = Color(0xFFC62828)
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        TabsCustomMDCard(
                            selectedTabIndex = selectedTabIndex,
                            onClick = { value -> selectedTabIndex = value }
                        )
                    }
                }

                is MovieDetailState.Error -> {

                }
            }

            when (selectedTabIndex) {
                0 -> {
                    ParentVideosCard(stateVds)
                }

                1 -> {
                    ParentReviewsCard(stateRvs, onClickLoadMore = {
                        vm.fetchReviews()
                    })
                }
            }
        }
    }
}
