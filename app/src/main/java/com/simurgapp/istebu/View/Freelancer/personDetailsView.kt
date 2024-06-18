package com.simurgapp.istebu.View.Freelancer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.toObject
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.Profile.PastProjectsSection
import com.simurgapp.istebu.View.Profile.ProfileInfoItem
import com.simurgapp.istebu.View.Profile.RatingSection
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.CommentsArea
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.ViewModel.MessagesViewModel
import java.util.UUID

@Composable
fun freelancerDetailsScreen(uid: String, navController: NavController) {
    val tempData = tempData()
    val firestoreUserRepository = com.simurgapp.istebu.Model.FirestoreUserRepository()

    var freelancer : MutableState<FreelancerClass> = remember {
        mutableStateOf(FreelancerClass())
    }
    val context = LocalContext.current
    val sharedPreferencesHelper = com.simurgapp.istebu.Model.SharedPreferencesHelper(context)
    val currentUserID = sharedPreferencesHelper.getUID() ?: ""
    var chatUID = remember {
        mutableStateOf("")
    }
    val MViewModel = MessagesViewModel()
    LaunchedEffect(key1 =uid) {
        firestoreUserRepository.getFreelancerByUID(uid, { document ->
            freelancer.value = document.toObject<FreelancerClass>()!!
            println("Current User ID: $currentUserID")
            println("Freelancer ID: ${freelancer.value.UID}")

            MViewModel.getChatID(currentUserID, freelancer.value.UID) { chatID ->

                if (chatID != null) {
                    chatUID.value = chatID
                    println( "Chat ID: ${chatUID.value}")
                } else {
                    chatUID.value = UUID.randomUUID().toString()
                    println( "Chat ID: ${chatUID.value}")
                }
            }
        }, { exception ->
            println("Failed to get freelancer: $exception")
        })




    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            CircleImage(imageUrl = freelancer.value.imageURL, size = 200)

            Text(
                text = "${freelancer.value.name} ${freelancer.value.surname}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = freelancer.value.careerFields.joinToString(", "),
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )


            RatingSection(rating =freelancer.value.rating)
            Column {
                FilledTonalButton(onClick = { navController.navigate("reviewScreen") }, text = "Rewiew")
                Text(text = "!! iş bittiğinde görünecek")
            }
            FilledTonalButton(onClick = { println(currentUserID)
                println(freelancer)

                navController.navigate("messageDetail/${chatUID.value}/${currentUserID}/${freelancer.value.UID}")}, text = "Send Message")
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Column {
                ProfileInfoItem(label = "Daily Price", value = "${freelancer.value.dailyPrice} $" )
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Email", value = freelancer.value.email)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Phone", value = freelancer.value.phoneNumber)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Education", value = freelancer.value.education)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Country", value = freelancer.value.country)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "City", value = freelancer.value.city)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Defination", value = freelancer.value.defination)
                Divider()
                PastProjectsSection(pastProjects =freelancer.value.pastProjects)
                Spacer(modifier =Modifier.height(48.dp))
                CommentsArea(comments =freelancer.value.comments)

            }
            Spacer(modifier = Modifier.height(64.dp))






        }
    }
}