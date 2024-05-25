package com.simurgapp.istebu.View.Freelancer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
            Text(text = freelancer.name + " " + freelancer.surname,
                fontSize = 24.sp, // Metin boyutunu büyüt
                fontWeight = FontWeight.Bold // Metni kalın yap

            )
            Text(text = freelancer.skills[0])
        }

    }
}