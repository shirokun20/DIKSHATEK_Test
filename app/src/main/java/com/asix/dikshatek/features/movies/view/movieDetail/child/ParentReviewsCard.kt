package com.asix.dikshatek.features.movies.view.movieDetail.child

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asix.dikshatek.features.movies.model.reviews.ReviewModel
import com.asix.dikshatek.features.movies.state.ReviewsState
import com.asix.dikshatek.features.movies.view.moviesByGenre.child.LoadMoreBtn

@Composable
fun ParentReviewsCard(stateRvs: State<ReviewsState>, onClickLoadMore: () -> Unit) {
    when (val currentState = stateRvs.value) {
        is ReviewsState.Loading -> {

        }

        is ReviewsState.Success -> {
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "List Review",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (currentState.data.isEmpty()) {
                    EmptyCard()
                } else {
                    currentState.data.forEach { res ->
                        ReviewMDCard(res)
                    }
                }
                if (currentState.isLoadMore) {
                    LoadMoreBtn(title = "Load More", onClick = onClickLoadMore)
                }
            }
        }

        is ReviewsState.Error -> {

        }
    }
}

@Composable
fun EmptyCard() {
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
            .padding(15.dp),
    ) {
        Text(
            "No data yet.", style = TextStyle(
                fontSize = 15.sp,
                color = Color(0xFF000000),
            )
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun ReviewMDCard(res: ReviewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .border(
                border = BorderStroke(1.dp, Color(0xFFEBEEF4)),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row {
            if (res.author_details.avatar_path != null) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${res.author_details.avatar_path}",
                    contentDescription = null,
                    modifier = Modifier
                        .width(70.dp)
                )
            } else {
                AsyncImage(
                    model = "https://suryacipta.com/wp-content/themes/consultix/images/no-image-found-360x250.png",
                    contentDescription = null,
                    modifier = Modifier
                        .width(70.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    res.author, style = TextStyle(
                        fontSize = 15.sp,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight(weight = 800)
                    )
                )
                Text(
                    text =
                    res.author_details.username.ifEmpty {
                        "Have no username"
                    },
                    maxLines = 4,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${res.author_details.rating}",
                    maxLines = 4,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}