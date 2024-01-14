package com.asix.dikshatek.features.movies.view.movieDetail.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asix.dikshatek.features.movies.model.genres.GenreModel
import com.asix.dikshatek.features.movies.model.movieDetail.MovieDetailResponseModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailCard(data: MovieDetailResponseModel) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp),
        ) {
            Column {
                Row {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/original${data.poster_path}",
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = data.original_title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = data.release_date,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Status: ${data.status}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(text = "Genres:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp,
                                alignment = Alignment.Start
                            ),
                        ) {
                            data.genres.forEach { item: GenreModel ->
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .clip(CircleShape)
                                        .background(
                                            color = Color(0xFFf2f2f2), shape = CircleShape
                                        )
                                ) {
                                    Text(
                                        text = item.name,
                                        fontSize = 17.sp,
                                        modifier = Modifier.padding(
                                            horizontal = 15.dp,
                                            vertical = 5.dp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                Text(
                    text = "Description",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = data.overview,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}