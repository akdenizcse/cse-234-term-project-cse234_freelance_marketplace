package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange


@Composable
fun SelectableDropDown(
    careerFields: List<String>,
    selectedIndices: MutableList<String>,
    text : String,
    onItemSelected: (Int) -> Unit
) {
    var dropControl by remember { mutableStateOf(false) }
    var text = remember { mutableStateOf(text) }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.66f)
            .height(50.dp)
            .border(
                BorderStroke(
                    width = 4.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Orange200,
                            darkerOrange
                        )
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(8.dp)
            .clickable {
                dropControl = true
            }

    ) {
        Icon(Icons.Default.ArrowForward, contentDescription = "Price Change")
        Text(text =text.value)
    }

    DropdownMenu(expanded = dropControl, onDismissRequest = { dropControl = false }, modifier = Modifier
        .background(
            Color.White
        )
        .border(2.dp, Orange200)) {
        careerFields.forEachIndexed { index, career ->
            val isSelected = selectedIndices.contains(career)
            DropdownMenuItem(
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isSelected) {
                            Icon(Icons.Outlined.Check, contentDescription = "Checked")
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(text = career, style = TextStyle(fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal))
                    }
                },
                onClick = {
                    onItemSelected(index)
                }
            )
        }
    }


}