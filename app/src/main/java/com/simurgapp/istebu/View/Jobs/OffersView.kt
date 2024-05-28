package com.simurgapp.istebu.View.Jobs

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.OffersClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.Profile.ProfileImageSection
import com.simurgapp.istebu.View.Profile.ProfileInfoItem
import com.simurgapp.istebu.View.UIElements.FilledTonalButton

/*
data class OffersClass(
    var UID: String,
    var price: Int,
    var estimatedTime: Int,
    var projectID: String,
    var freelancerID: String,
    var date: String,
    var isAccepted: Boolean,
    var isRejected: Boolean,
    var isFinished: Boolean,
    var comment: String,
    )
 */

@Composable
fun OffersView(navController: NavController) {
    var tempData = tempData()
    val imageSize = 200
    var offers = OffersClass(
        UID = "offer001",
        price = 30,
        estimatedTime = 30,
        projectID = "proj123",
        freelancerID = "freelancer001",
        date = "2024-05-01",
        isAccepted = false,
        isRejected = false,
        isFinished = false,
        comment = "Initial offer for the project."
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ProfileImageSection(tempData = tempData, imageSize = imageSize)
            androidx.compose.material3.Text(
                text = "Ali Berk Yeşilduman",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier

            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column{
                    ProfileInfoItem("Price", offers.price.toString() + " TL")
                    ProfileInfoItem("Estimated Time", offers.estimatedTime.toString() + " days")
                }


                Spacer(modifier = Modifier.width(200.dp))
            }
            Text(text = "Comment" , fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(text = offers.comment)
            FilledTonalButton(onClick = { navController.navigate("paymentPage") }, text = "Accept Offer")



            Spacer(modifier = Modifier.height(64.dp))


        }
    }
}
