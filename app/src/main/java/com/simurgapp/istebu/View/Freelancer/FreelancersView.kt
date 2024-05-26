package com.simurgapp.istebu.View.Freelancer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.GenericListView
import com.simurgapp.istebu.View.UIElements.PicTextItem
import com.simurgapp.istebu.ViewModel.BackViewModel
import kotlin.random.Random.Default.nextInt


@Composable
fun FreelancersScreen(navController: NavController) {
    val tempData = tempData()
    val freelancerClass : MutableList<FreelancerClass> = tempData.getFreeLancerClass()
    val tempList = mutableListOf<String>()
    val skills = mutableListOf<String>()
    val backViewModel = BackViewModel()




Box (modifier = Modifier.padding(bottom = 64.dp)){
    GenericListView(freelancerClass) { item , index->
        println("girdi $index")
        println(item.name)
        println(item.UID)
        println(freelancerClass[index].careerFields[0])
        println("devam ediyor $index")
        PicTextItem(freelancerClass[index].UID, freelancerClass[index].name,freelancerClass[index].careerFields[nextInt(3)] , freelancerClass[index].imageURL){
            // backViewModel.increment()
            navController.navigate("freelancerDetailScreen/${index}")
        }
        println("çıktı $index")
    }
}


}
