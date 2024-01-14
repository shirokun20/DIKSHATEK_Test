package com.asix.dikshatek.features.movies.view.movieDetail.child

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asix.dikshatek.features.movies.model.videos.VideoModel
import com.asix.dikshatek.features.movies.state.VideosState

@Composable
fun ParentVideosCard(stateVds: State<VideosState>) {
    when (val currentState = stateVds.value) {
        is VideosState.Loading -> {

        }

        is VideosState.Success -> {
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "List Video",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (currentState.data.isEmpty()) {
                    EmptyCard()
                } else {
                    currentState.data.forEach { res ->
                        VideoMDCard(res)
                    }
                }
            }
        }

        is VideosState.Error -> {

        }
    }
}

@Composable
fun VideoMDCard(res: VideoModel) {
    val context = LocalContext.current
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
            .clickable {
                openYouTubeVideo(res.key, context)
            }
            .padding(10.dp)
    ) {
        Column {
            Row {
                AsyncImage(
                    model = "https://img.youtube.com/vi/${res.key}/mqdefault.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        res.name, style = TextStyle(
                            fontSize = 15.sp,
                            color = Color(0xFF000000),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = res.type,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text =
                        res.site.ifEmpty {
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
                    text = "See the video",
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
}

fun openYouTubeVideo(videoId: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
    context.startActivity(intent)
}