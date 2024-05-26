package com.simurgapp.istebu.View.UIElements


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.Model.tempData

@Composable
fun CommentsArea(comments: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(2.dp, Color(0xFF006400), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Comments",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006400)
            )
            IconButton(
                onClick = { /* TODO: Show all comments */ }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Show all",
                    tint = Color(0xFF006400)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        comments.forEachIndexed { index, comment ->
            PicTextItem(sire = "", title = "Zehra Bozkurt", subtitle = comment, imageUrl = tempData().images.random()){
            }
            if (index < comments.size - 1) {
                Divider(
                    color = Color.Gray.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

