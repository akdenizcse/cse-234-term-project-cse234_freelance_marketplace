package com.simurgapp.istebu.View.Profile

import LoginsigninViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.CommentsArea
import com.simurgapp.istebu.View.UIElements.FilledTonalButton


@Composable
fun ProfileView(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    val loginViewModel = LoginsigninViewModel(sharedPreferencesHelper)
    var tempData = tempData()
    val imageSize = 200

    val isUserCreated by loginViewModel.isUserCreated.collectAsState()
    val isFreelancerCreated by loginViewModel.isFreelancerCreated.collectAsState()

    val currentUser = FreelancerClass(
        UID = "1",
        name = "Ali Berk",
        surname = "Yeşilduman",
        imageURL = tempData.images.random(),
        dailyPrice = 100,
        email = "berkyesilduman@gmail.com",
        phoneNumber = "5459328569",
        education = "Computer Engineering",
        rating = 4.5f,
        pastProjects = mutableListOf("Project A", "Project B", "Project C"),
        ongoingProjects = mutableListOf("Project D", "Project E"),
        completedGivenProjects = mutableListOf("Project F", "Project G"),
        ongoingGivenProjects = mutableListOf("Project H", "Project I"),
        careerFields = mutableListOf("Java", "Kotlin", "Python", "C++"),
        reviews = mutableListOf("Excellent work!", "Very professional."),
        comments = mutableListOf("Great work!", "Very professional.", "Highly recommend!"),
        country = "Turkey",
        job = "Software Developer",
        city = "Istanbul",

        )

    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ProfileImageSection(tempData = tempData, imageSize = imageSize)
            Text(
                text = "${currentUser.name} ${currentUser.surname}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                //.border(2.dp, Color(0xFF006400), RoundedCornerShape(12.dp))

            )
            Text(
                text = currentUser.careerFields.joinToString(", "),
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(12.dp))

            PastProjectsSection(pastProjects = currentUser.pastProjects, "Past Projects",{navController.navigate("pastProjectsView")})
            PastProjectsSection(pastProjects = currentUser.ongoingProjects, "Ongoing Projects",{navController.navigate("ongoingProjectsView")} )
            Spacer(modifier = Modifier.height(16.dp))
            RatingSection(rating = currentUser.rating)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                ProfileInfoSection(currentUser = currentUser)
                Spacer(modifier = Modifier.width(200.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (!isFreelancerCreated) {

                FilledTonalButton(
                    onClick = {
                        if(isUserCreated) {
                            navController.navigate("getFreelancerInfoView")
                        } else {
                            val cameF = true
                            navController.navigate("getUserInfoView/${cameF}")
                        }
                    },
                    text = "Freelancer Olmak İstiyorum"
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
            if (!isUserCreated) {
                FilledTonalButton(
                    onClick = {
                        var cameF = false
                        navController.navigate("getUserInfoView/${cameF}") },
                    text = "Üyeliği Tamamla"
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Spacer(modifier = Modifier.height(16.dp))
            }


            CommentsArea(comments = currentUser.comments)


            Spacer(modifier = Modifier.height(64.dp))


        }
    }
}



@Composable
fun ProfileImageSection(tempData: tempData, imageSize: Int) {
    Box {
        CircleImage(
            imageUrl = tempData.images.random(),
            size = imageSize,
            modifier = Modifier
                .size(imageSize.dp)
                .clip(CircleShape)
            //.shadow(2.5.dp, CircleShape)
        )
        IconButton(
            onClick = {
            },
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-imageSize / 12).dp, y = (imageSize / -12).dp)
                .background(Color.LightGray, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Camera Icon",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun ProfileInfoSection(currentUser: FreelancerClass) {
    Column {

        Spacer(modifier = Modifier.size(8.dp))
        ProfileInfoItem(label = "Email", value = currentUser.email)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "Phone", value = currentUser.phoneNumber)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "Education", value = currentUser.education)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "Country", value =currentUser.country )
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "City", value = currentUser.city)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}


@Composable
fun RatingSection(rating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Rating:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = if (i <= rating.toInt()) Color(0xFFFFBF00) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$rating/5", fontSize = 16.sp)
    }
}
@Composable
fun PastProjectsSection(pastProjects: List<String> ,title : String = "Past Projects" , onClick : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            IconButton(
                onClick = { onClick()},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Show all")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            pastProjects.forEach { project ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(150.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF2C4E80),
                                        Color(0xFF00215E)
                                    )
                                )
                            )
                    ) {
                        Text(
                            text = project,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}