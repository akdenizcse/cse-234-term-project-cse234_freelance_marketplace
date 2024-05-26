package com.simurgapp.istebu.View.Freelancer

import android.widget.ListView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.GenericListView
import com.simurgapp.istebu.View.UIElements.PicTextItem

@Composable


fun CareerFieldsView(navController: NavController) {
    Box (modifier = Modifier.padding(bottom = 64.dp)){
        GenericListView(list = tempData().careerFields) { item , index->

            PicTextItem(sire = index.toString(), title = item[0], subtitle = "", imageUrl = item[1]){
                navController.navigate("fieldsDetailsView/${index}")
            }

        }
    }


    }
