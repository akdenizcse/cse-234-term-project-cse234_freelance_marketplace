package com.simurgapp.istebu.View.Messages


import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.PicTextItem
import com.simurgapp.istebu.ViewModel.MessagesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.toObject
import com.simurgapp.istebu.Model.Message
import com.simurgapp.istebu.Model.UserClass
import com.simurgapp.istebu.ui.theme.Orange500

@Composable
fun MessagesDetail(chatID : String  ,senderId : String,reciverId : String, viewModel: MessagesViewModel = viewModel()) {

    var message = remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()
    val reciver  = remember {
        mutableStateOf(UserClass())
    }
    val firestoreUserRepository = com.simurgapp.istebu.Model.FirestoreUserRepository()

    LaunchedEffect(key1 = chatID){
        viewModel.fetchMessages(chatID)
        firestoreUserRepository.getUserByUID(reciverId, { document ->
            reciver.value = document
        }, { exception ->
            println("Failed to get freelancer: $exception")
        })
        // app bar ı görünmez yap


    }

    Column(modifier = Modifier.fillMaxSize()) {
        // add backgraound color
        Box( modifier = Modifier
            .fillMaxWidth()

            .background(Orange200), contentAlignment = Alignment.CenterStart){
            PicTextItem(sire = "", title = "${reciver.value.name} ${reciver.value.surname}", subtitle = reciver.value.job , imageUrl = reciver.value.imageURL ) {

            }


        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true,
            contentPadding = PaddingValues(16.dp)
        ) {

            if (messages.isNotEmpty()) {
                items(messages.size) { index ->
                    MessageCard(message = messages[messages.size - 1 - index].message , sender = messages[messages.size - 1 - index].senderId == senderId)
                }
            }
        }
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            TextFieldOne(labelText = "Messages", leadingIconOne = null , colorOne = Orange200, colorTwo = darkerOrange , text = message )
            FilledTonalButton(onClick = {
                if (message.value.isNotEmpty()) {
                    viewModel.sendMessage(chatID, Message(senderId,reciverId,"", message.value))
                    message.value = ""
                }}, text ="Send" )

        }
        Spacer(modifier = Modifier.height(64.dp))

    }

}

@Composable
fun MessageCard(message: String, sender: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (sender) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .padding(2.dp),
            colors = CardDefaults.cardColors(if (sender) Orange500 else Orange200),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = message,
                fontSize = 18.sp,
                modifier = Modifier.padding(12.dp),
                color = Color.Black
            )
        }
    }
}