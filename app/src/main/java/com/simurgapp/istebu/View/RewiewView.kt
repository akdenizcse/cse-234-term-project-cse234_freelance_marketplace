package com.simurgapp.istebu.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.drawscope.Stroke
import com.simurgapp.istebu.ui.theme.IsteBuTheme


@Composable
fun ReviewScreen() {
    var communicationRating by remember { mutableStateOf(4.0f) }
    var serviceQualityRating by remember { mutableStateOf(3.5f) }
    var timingRating by remember { mutableStateOf(1.0f) }
    var comment = remember { mutableStateOf("") }
    var isPortfolioPermission by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Giving your opinions and rating about the order and the freelancer is very valuable for the Bionluk Freelancer community..", fontSize = 14.sp)

        RatingBar("Communication", communicationRating) { communicationRating = it }
        RatingBar("Service Quality", serviceQualityRating) { serviceQualityRating = it }
        RatingBar("Timing", timingRating) { timingRating = it }


        TextFieldOne(labelText = "Comment", leadingIconOne = Icons.Default.Comment, colorOne = Orange200, colorTwo = darkerOrange, text = comment )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = isPortfolioPermission,
                onCheckedChange = { isPortfolioPermission = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("I allow the freelancer to add this work to her portfolio.")
        }

        com.simurgapp.istebu.View.UIElements.FilledTonalButton(onClick = { /*TODO*/ }, text = "Submit Rewiew" )
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
fun RatingBar(label: String, rating: Float, onRatingChanged: (Float) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label)
        Slider(
            value = rating,
            onValueChange = onRatingChanged,
            valueRange = 0f..5f,
            steps = 4,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(rating.toString())
    }
}



