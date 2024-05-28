package com.simurgapp.istebu.View.RegistrationProcedure


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.LocalAtm
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import androidx.compose.foundation.layout.Arrangement as Arrangement
import androidx.compose.material3.MaterialTheme

import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.SelectableDropDown


@Composable
fun GetFreelancerInfoView() {
    val dailyPrice = remember { mutableStateOf("") }
    val education = remember { mutableStateOf("") }
    val country = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val definition = remember { mutableStateOf("") }
    val careerFields = listOf(
        "Graphic Design",
        "Programming",
        "Translation",
        "Teaching",
        "Fitness Training",
        "UI/UX Design",
        "Video Editing",
        "Voiceover",
        "Life Coaching",
        "Nutrition Coach",
        "Photoshop",
        "Illustration",
        "Animation",
        "Social Media Management",
        "Marketing",
        "Copywriting"
    ).sorted()

    val selectedIndices = remember { mutableStateListOf<String>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Freelancer Information",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TextFieldOne(
                labelText = "Daily Price",
                leadingIconOne = Icons.Default.LocalAtm,
                colorOne = Orange200,
                colorTwo = darkerOrange,
                text = dailyPrice
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextFieldOne(
                labelText = "Education",
                leadingIconOne = Icons.Default.School,
                colorOne = Orange200,
                colorTwo = darkerOrange,
                text = education
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextFieldOne(
                labelText = "Definition",
                leadingIconOne = Icons.Default.Comment,
                colorOne = Orange200,
                colorTwo = darkerOrange,
                text = definition
            )
            Spacer(modifier = Modifier.height(16.dp))

            SelectableDropDown(
                careerFields = careerFields,
                selectedIndices = selectedIndices,
                onItemSelected = { index ->
                    if (selectedIndices.contains(careerFields[index])) {
                        selectedIndices.remove(careerFields[index])
                    } else {
                        selectedIndices.add(careerFields[index])
                    }
                }
            )
            Spacer(modifier =Modifier.height(16.dp))
            SelectedFieldsView(selectedIndices.sortedBy { str ->
                str.length
            }) { field ->
                selectedIndices.remove(field)
            }

            Spacer(modifier = Modifier.height(24.dp))

            FilledTonalButton(onClick = { /*TODO*/ }, text = "Save")

            Spacer(modifier = Modifier.height(64.dp))

        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedFieldsView(selectedFields: List<String>, onRemove: (String) -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),


    ) {
        selectedFields.forEach { field ->
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Orange200, darkerOrange)
                        ),
                        shape = RoundedCornerShape(36.dp)
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = field,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    IconButton(
                        onClick = { onRemove(field) },) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remove",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}