package com.simurgapp.istebu.View.Freelancer

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.GenericListView
import com.simurgapp.istebu.View.UIElements.PicTextItem
import com.simurgapp.istebu.ViewModel.BackViewModel


@Composable
fun FreelancersScreen(navController: NavController) {
    val tempData = tempData()
    val freelancerClass : MutableList<FreelancerClass> = tempData.getFreeLancerClass()
    val tempList = mutableListOf<String>()
    val skills = mutableListOf<String>()
    val backViewModel = BackViewModel()


    for (i in 0..94) {



        try {
            //freelancerClass.add(FreelancerClass("UID$i", tempData.names[i], "Surname$i", tempData.images.random(), "email$i", "phoneNumber$i", "education$i", 0.0f,tempData.names,tempData.names,tempData.jobs,tempData.jobs , "country$i", "city$i"))

        }catch (
        e: IndexOutOfBoundsException
        ){
            println(e.message)
        }


    }
    GenericListView(freelancerClass) { item , index->
        println("girdi $index")
        println(item.name)
        println(item.UID)

        println(freelancerClass[index].skills[0])
        PicTextItem(freelancerClass[index].UID, freelancerClass[index].name,freelancerClass[index].skills[index] , freelancerClass[index].imageURL){
           // backViewModel.increment()
            navController.navigate("freelancerDetailScreen/${index}")



        }
    }
}
