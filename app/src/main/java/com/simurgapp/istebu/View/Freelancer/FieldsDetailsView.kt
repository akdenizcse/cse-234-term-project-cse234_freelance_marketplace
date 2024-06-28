package com.simurgapp.istebu.View.Freelancer


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.GenericListView
import com.simurgapp.istebu.View.UIElements.PicTextItem

@Composable
fun FieldsDetailsView(index : Int,navController: NavController,goTo : String) {
    var list = tempData().careerFields[index].subList(2, tempData().careerFields[index].size)
    GenericListView(list = list) { item , index->
        PicTextItem(sire = "i", title = item, subtitle ="" , imageUrl = tempData().images.random()){
            val route = "${goTo}/$item"
            println("route : $route girdiiiiiiiiiiiiii")
            println(item)
            navController.navigate("${route}")

        }

    }


}