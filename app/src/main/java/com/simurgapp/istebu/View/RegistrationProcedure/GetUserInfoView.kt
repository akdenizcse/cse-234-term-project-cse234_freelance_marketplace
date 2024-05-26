package com.simurgapp.istebu.View.RegistrationProcedure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.LocalAtm
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange

@Composable
fun GetUserInfoView() {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val country = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val job = remember { mutableStateOf("") }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldOne(labelText = "Name", leadingIconOne = Icons.Default.Person, colorOne = Orange200 , colorTwo = darkerOrange , text = name )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldOne(labelText = "Surname", leadingIconOne = Icons.Default.People, colorOne = Orange200 , colorTwo = darkerOrange , text = surname )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldOne(labelText = "Country", leadingIconOne = Icons.Default.LocationOn, colorOne = Orange200 , colorTwo = darkerOrange , text = country )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldOne(labelText = "City", leadingIconOne = Icons.Default.LocationCity, colorOne = Orange200 , colorTwo = darkerOrange , text = city )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldOne(labelText = "Phone Number", leadingIconOne = Icons.Default.Phone, colorOne = Orange200 , colorTwo = darkerOrange , text = phoneNumber )

            Spacer(modifier = Modifier.height(16.dp))
            TextFieldOne(labelText = "Job", leadingIconOne = Icons.Default.Work, colorOne = Orange200 , colorTwo = darkerOrange , text = job )
            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalButton(onClick = { /*TODO*/ }, text =    "Save")
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}
