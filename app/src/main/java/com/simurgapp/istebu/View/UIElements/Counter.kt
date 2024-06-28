package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.ui.theme.darkerOrange

@Composable
fun Counter(change: (Int) -> Unit , text : String) {
    var count = remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = count.value.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    IconButtonOne(
                        icon = Icons.Default.Add,
                        contentDescription = "Increment",
                        onClick = {

                            count.value++ },
                    )
                    IconButtonOne(
                        icon = Icons.Default.Remove,
                        contentDescription = "Decrement",
                        onClick = {
                            if (count.value > 0)
                                count.value-- },
                    )
                }
            }
            Text(text = text)
        }

    }
}