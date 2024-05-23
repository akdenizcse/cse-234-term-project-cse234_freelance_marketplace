package com.simurgapp.istebu.View.UIElements

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.simurgapp.istebu.ViewModel.BackViewModel

@Composable
fun MyTopAppBar(navController: NavHostController, backViewModel: BackViewModel) {

    val isBack by backViewModel.isBack.collectAsState()

    TopAppBar(
        backgroundColor = Color(255, 165, 0),
        contentColor = Color.Black,
        title = { Text(text = "İşteBu" , fontSize = 24.sp , textAlign = TextAlign.Center , color = Color.White) },

        navigationIcon = {
            if (isBack > 0) {
                IconButton(onClick = {
                    backViewModel.decrement()
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {

        }
    )
}