package com.simurgapp.istebu.View.Messages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.Model.UserClass

import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.IconButtonOne
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ViewModel.MessagesViewModel
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MessagesScreen(navController: NavController ,viewModel: MessagesViewModel = viewModel()) {
    val tempData = tempData()
    val text =  remember {
        mutableStateOf("")
    }
    val firestoreUserRepository = com.simurgapp.istebu.Model.FirestoreUserRepository()
    val chats by viewModel.chats.collectAsState()
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val currentUserID = sharedPreferencesHelper.getUID() ?: ""
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchChatsByUID(currentUserID)
    }

    Column (modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())){

        Spacer(modifier = Modifier.padding(16.dp))

        Row (modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.CenterVertically ){
            TextFieldOne(labelText = "Search", leadingIconOne = Icons.Default.Search , colorOne = Orange200, colorTwo = darkerOrange , text = text )
            IconButtonOne(icon = Icons.Default.Search, contentDescription =  "search", onClick = { } , buttonSize = 48 , iconSize = 48)
        }
        Spacer(modifier = Modifier.padding(16.dp))

        for (chat in chats){
            val chatMembers = chat.chatMembers.toMutableList()
            chatMembers.remove(currentUserID)
            val reciverId = chatMembers[0]
            val name = remember {
                mutableStateOf("")
            }
            val surname = remember {
                mutableStateOf("")
            }
            println(reciverId)
            viewModel.getUserByUID(reciverId,
                onSuccess = { users ->
                    println("User fetched successfully " + users)
                    name.value = users.name
                    surname.value = users.surname
                },
                onFailure = { exception -> println("Error fetching user by UID: ${exception.message}") }

            )
            var imageURL = remember {
                mutableStateOf("")
            }
            firestoreUserRepository.getImageForUser(reciverId, { url ->
                imageURL.value = url.toString()
            }, { exception ->
                println("Failed to get image: $exception")
            })

            MessageItem(
                imageURL = imageURL.value,
                message = chat.lastMessage.message,
                name = name.value,
                surname = surname.value,
                date = dateFormat.format(chat.lastMessage.timestamp),
                navController = navController,
                chatId = chat.chatId,
                senderId = currentUserID,
                reciverId = reciverId
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
fun MessageItem(imageURL : String, message : String ,name: String , surname: String , date: String,navController: NavController , chatId : String , senderId : String, reciverId : String) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = androidx.compose.ui.graphics.Color.Gray)
            .clickable {
                println("chat ıd $chatId")
                navController.navigate("messageDetail/$chatId/$senderId/$reciverId")
            }
            .padding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ){
        CircleImage(imageUrl = imageURL)
        Column {
            Text(text = "$name $surname", fontWeight = FontWeight.Bold , fontSize = 24.sp)
            Text(text = message , fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = date)

    }
}