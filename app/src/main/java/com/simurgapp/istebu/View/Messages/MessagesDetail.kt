package com.simurgapp.istebu.View.Messages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.PicTextItem


@Composable
fun MessagesDetail()  {
    var message = remember { mutableStateOf("") }
    var messages = remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.fillMaxSize()) {
        PicTextItem(sire = "", title = "Zehra Bozkurt", subtitle ="" , imageUrl =tempData().images.random() ) {

        }
       LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true,
            contentPadding = PaddingValues(16.dp)
        ) {
            items(messages.value.size) { index ->
                MessageCard(message = messages.value[messages.value.size - 1 - index])
            }
        }
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            TextFieldOne(labelText = "Messages", leadingIconOne = null , colorOne = Orange200, colorTwo = darkerOrange , text = message )
            FilledTonalButton(onClick = {
                if (message.value.isNotEmpty()) {
                messages.value = messages.value + message.value
                message.value = ""
            }}, text ="Send" )

        }
        Spacer(modifier = Modifier.height(64.dp))

    }
}

@Composable
fun MessageCard(message: String) {
    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    ) {
        Text(
            text = message,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}
