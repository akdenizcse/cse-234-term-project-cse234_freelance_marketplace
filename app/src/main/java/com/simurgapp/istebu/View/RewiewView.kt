package com.simurgapp.istebu.View

import android.widget.RatingBar
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
import com.simurgapp.istebu.ui.theme.starsYellow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import com.simurgapp.istebu.ui.theme.IsteBuTheme


@Composable
fun ReviewScreen(freelanerID: String) {
    var communicationRating by remember { mutableStateOf(4.0f) }
    var serviceQualityRating by remember { mutableStateOf(3.5f) }
    var timingRating by remember { mutableStateOf(1.0f) }
    var comment = remember { mutableStateOf("") }
    val firestoreUserRepository = com.simurgapp.istebu.Model.FirestoreUserRepository()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Giving your opinions and rating about the order and the freelancer is very valuable for the Bionluk Freelancer community..", fontSize = 14.sp)
        StarsBar(rating = communicationRating, starCount = 5, starSize = 32f, starColor = starsYellow, emptyStarColor = Color.Gray)
        RatingBar("Communication", communicationRating) { communicationRating = it }
        StarsBar(rating = serviceQualityRating, starCount = 5, starSize = 32f, starColor = starsYellow, emptyStarColor = Color.Gray)

        RatingBar("Service Quality", serviceQualityRating) { serviceQualityRating = it }
        StarsBar(rating = timingRating, starCount = 5, starSize = 32f, starColor = starsYellow, emptyStarColor = Color.Gray)

        RatingBar("Timing", timingRating) { timingRating = it }


        TextFieldOne(labelText = "Comment", leadingIconOne = Icons.Default.Comment, colorOne = Orange200, colorTwo = darkerOrange, text = comment )

        com.simurgapp.istebu.View.UIElements.FilledTonalButton(onClick = {
            val rating = (communicationRating+ serviceQualityRating + timingRating) / 3
            firestoreUserRepository.addCommitAndRatingToFreeLancer(freelanerID,comment.value,rating)

        }, text = "Submit Rewiew" )
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
@Composable
fun drawStar(starSize: Float , color: Color , i : Int  ,full : Int , partial : Float ) {
    val starPath = Path().apply {
        moveTo(0.5f, 0.0f)
        lineTo(0.61f, 0.35f)
        lineTo(0.98f, 0.35f)
        lineTo(0.68f, 0.57f)
        lineTo(0.79f, 0.91f)
        lineTo(0.5f, 0.70f)
        lineTo(0.21f, 0.91f)
        lineTo(0.32f, 0.57f)
        lineTo(0.02f, 0.35f)
        lineTo(0.39f, 0.35f)
        close()
    }
    Canvas(modifier = Modifier
        .width(50.dp)
        .height(50.dp)
        .padding(16.dp)) {
        drawPath(
            path = starPath, color = color, style = Stroke(width = starSize)


        )
        if (i == full && partial > 0) {
            clipRect(right = size.width * partial) {
                drawPath(
                    path = starPath,
                    color = color,
                    style = Stroke(width = 5f)
                )
            }
        }
    }
}
@Composable
fun StarsBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    starSize: Float = 48f,
    starColor: Color = starsYellow,
    emptyStarColor: Color = Color.Gray) {
    val full = rating.toInt()
    val partial = rating - full

    Row {
        for (i in 0 until starCount) {
            drawStar(starSize, if (i < full) starColor else emptyStarColor, i, full, partial)
            Spacer(modifier = Modifier.width(8.dp))

        }


    }


}