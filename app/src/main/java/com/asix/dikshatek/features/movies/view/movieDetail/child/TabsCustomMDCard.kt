package com.asix.dikshatek.features.movies.view.movieDetail.child

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabsCustomMDCard(
    selectedTabIndex: Int,
    onClick: (selectedTabIndex: Int) -> Unit,
) {
    val tabs = listOf("Videos", "Reviews")
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 4.dp))
            .border(
                border = BorderStroke(
                    width = 1.dp, Color(0xFFD7E0E3),
                ), shape = RoundedCornerShape(size = 4.dp)
            )
            .background(
                color = Color.White, shape = RoundedCornerShape(size = 4.dp)
            ),
        contentColor = Color(0xFFB88A2F),
        indicator = { },
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier
                    .background(color = if (selectedTabIndex == index) Color(0xFFC62828) else Color.White)
                    .border(
                        border = BorderStroke(
                            width = if (selectedTabIndex == index) 2.dp else 0.dp,
                            color = if (selectedTabIndex == index) Color(0xFFC62828) else Color(
                                0xFFD7E0E3
                            )
                        ),
                    ),
                selected = selectedTabIndex == index,
                onClick = { onClick(index) },
            ) {
                Box(modifier = Modifier.padding(vertical = 10.dp)) {
                    Text(
                        text = title, style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(600),
                            color = if (selectedTabIndex == index) Color(0xFFFFFFFF) else Color.Black,
                        )
                    )
                }
            }
        }
    }
}