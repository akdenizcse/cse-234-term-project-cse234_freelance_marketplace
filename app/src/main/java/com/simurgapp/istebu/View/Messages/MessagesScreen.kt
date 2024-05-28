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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.IconButtonOne
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange

@Composable
fun MessagesScreen(navController: NavController) {
    val tempData = tempData()
    var text =  remember {
        mutableStateOf("")
    }
    Column (modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())){

        Spacer(modifier = Modifier.padding(16.dp))

        Row (modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.CenterVertically ){
            TextFieldOne(labelText = "Search", leadingIconOne = Icons.Default.Search , colorOne = Orange200, colorTwo = darkerOrange , text = text )
            IconButtonOne(icon = Icons.Default.Search, contentDescription =  "search", onClick = { /*TODO*/ } , buttonSize = 48 , iconSize = 48)
        }
        Spacer(modifier = Modifier.padding(16.dp))

        for (i in 0..10) {
            MessageItem(
                imageURL = tempData.getFreeLancerClass()[i].imageURL,
                message = "Hello, I am interested",
                name = tempData.getFreeLancerClass()[i].name,
                surname = tempData.getFreeLancerClass()[i].surname,
                date = "2024-05-01",
                navController = navController
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
fun MessageItem(imageURL : String, message : String ,name: String , surname: String , date: String,navController: NavController){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = androidx.compose.ui.graphics.Color.Gray)
            .clickable { navController.navigate("messageDetail") }
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