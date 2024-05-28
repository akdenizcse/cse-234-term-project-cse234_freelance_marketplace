package com.simurgapp.istebu.View.Freelancer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.Profile.PastProjectsSection
import com.simurgapp.istebu.View.Profile.ProfileInfoItem
import com.simurgapp.istebu.View.Profile.RatingSection
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.CommentsArea
import com.simurgapp.istebu.View.UIElements.FilledTonalButton

@Composable
fun freelancerDetailsScreen(index: Int) {
    val tempData = tempData()
    val freelancer = tempData.getFreeLancerClass()[index]

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
            CircleImage(imageUrl = freelancer.imageURL, size = 200)

            Text(
                text = "${freelancer.name} ${freelancer.surname}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = freelancer.careerFields.joinToString(", "),
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )


                RatingSection(rating =freelancer.rating)
                FilledTonalButton(onClick = { /*TODO*/ }, text = "Send Message")
                Divider(modifier = Modifier.padding(vertical = 8.dp))

            Column {
                ProfileInfoItem(label = "Daily Price", value = "${freelancer.dailyPrice} $" )
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Email", value = freelancer.email)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Phone", value = freelancer.phoneNumber)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Education", value = freelancer.education)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Country", value = freelancer.country)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "City", value = freelancer.city)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ProfileInfoItem(label = "Defination", value = freelancer.defination)
                Divider()
                PastProjectsSection(pastProjects =freelancer.pastProjects)
                Spacer(modifier =Modifier.height(48.dp))
                CommentsArea(comments =freelancer.comments)

            }
            Spacer(modifier = Modifier.height(64.dp))






        }
    }
}



