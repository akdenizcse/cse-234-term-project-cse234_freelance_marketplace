package com.simurgapp.istebu.View.Profile

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.UserClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.R
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.ui.theme.Orange500
import com.simurgapp.istebu.ui.theme.darkerOrange

@Composable
fun ProfileView() {
    var tempData = tempData()
    val imageSize = 200
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
        skills = mutableListOf("Java", "Kotlin", "Python", "C++"),
        reviews = mutableListOf("Excellent work!", "Very professional."),
        comments = mutableListOf("Great work!", "Very professional.", "Highly recommend!"),
        country = "Turkey",
        city = "Istanbul"
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            ProfileImageSection(tempData = tempData, imageSize = imageSize)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${currentUser.name} ${currentUser.surname}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .border(2.dp, Color(0xFF006400), RoundedCornerShape(12.dp))
                    .fillMaxWidth(0.9f)
                    .padding(16.dp)
            )
            PastProjectsSection(pastProjects = currentUser.pastProjects)
            Spacer(modifier = Modifier.height(16.dp))
            RatingSection(rating = currentUser.rating)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                ProfileInfoSection(currentUser = currentUser)
                Spacer(modifier = Modifier.width(200.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            CommentsSection(comments = currentUser.comments)
            Spacer(modifier = Modifier.height(16.dp))

            FilledTonalButton(onClick = { /*TODO*/ }, text = "Freelancer Olmak İstiyorum")

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
                .shadow(2.5.dp, CircleShape)
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
        ProfileInfoItem(label = "Phone", value = currentUser.phoneNumber)
        ProfileInfoItem(label = "Education", value = currentUser.education)
        ProfileInfoItem(label = "Skills", value = currentUser.skills.joinToString(", "))
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
                tint = if (i <= rating.toInt()) Color.Yellow else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$rating/5", fontSize = 16.sp)
    }
}

@Composable
fun PastProjectsSection(pastProjects: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Text(text = "Past Projects", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            pastProjects.forEach { project ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(120.dp)
                        .height(80.dp),

                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = project, fontSize = 14.sp, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Composable
fun CommentsSection(comments: List<String>) {
    Column(
        modifier = Modifier
            .border(1.dp, Color(0xFF006400), RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Comments", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        comments.forEach { comment ->
            Text(
                text = "\"$comment\"",
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
    }
}
