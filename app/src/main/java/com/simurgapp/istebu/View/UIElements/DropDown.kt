package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simurgapp.istebu.R
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange

//Create DropDown Menu
@Composable
fun DropDown(list : List<String> , onSelected : (Int) -> Unit){
    var dropControl =  remember {
        mutableStateOf(false)
    }
    var selectIndex = remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(0.66f)
                .height(50.dp)
                .border(
                    BorderStroke(
                        width = 4.dp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Orange200,
                                darkerOrange
                            )
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(8.dp)
                .clickable {
                    dropControl.value = true
                }

        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Price Change")
            Text(text = list[selectIndex.value])
        }
        DropdownMenu(modifier = Modifier.background(color = Color.White).border(2.dp, darkerOrange),expanded = dropControl.value, onDismissRequest = { dropControl.value = false } ) {

            list.forEachIndexed { index, strings ->
                DropdownMenuItem(
                    text = { Text(text = strings) },
                    onClick = {
                        dropControl.value = false
                        selectIndex.value = index
                        onSelected(index)
                    })
            }

        }

    }
}