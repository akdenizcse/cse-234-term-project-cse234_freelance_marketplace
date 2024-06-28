package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange


@Composable
fun FilledTonalButton(onClick: () -> Unit, text : String ,fontSize : Int = 20 ,isFillMaxWidth : Boolean = false) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Orange200, darkerOrange)
    )

    Box(
        modifier = Modifier
            .background(brush = gradient, shape = RoundedCornerShape(20.dp))
            .padding(1.dp),

        ) {
        androidx.compose.material3.FilledTonalButton(
            onClick = onClick,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(22.dp),
            modifier = if (isFillMaxWidth) {
                Modifier.fillMaxWidth(0.4f)
            } else {
                Modifier
            }
        ) {
            Text(text , fontSize = fontSize.sp)

        }
    }
}



@Composable
fun IconButtonOne(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    buttonSize: Int = 48,
    iconSize: Int = 24
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.width(buttonSize.dp).height(buttonSize.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.width(iconSize.dp).height(iconSize.dp)
        )
    }
}
