package com.simurgapp.istebu.View.Freelancer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.CircleImage

@Composable
fun freelancerDetailsScreen(index: Int) {
    val tempData = tempData()
    val freelancer = tempData.getFreeLancerClass()[index]
    println("freeDetailsScreen e girdi")
    Box(
        modifier = Modifier.fillMaxSize() ,
        contentAlignment = Alignment.Center
    ) {
        Column()
        {
            CircleImage(imageUrl =freelancer.imageURL , size = 200)
            Text(text = freelancer.name + " " + freelancer.surname)
            Text(text = freelancer.skills[0])
        }

    }
}