package com.simurgapp.istebu.View.UIElements

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.firestore.admin.v1.Index
import com.simurgapp.istebu.ui.theme.Orange500
import kotlinx.coroutines.flow.MutableStateFlow

sealed class BottomNavigationBar(val route: String, val title: String, val icon: ImageVector) {
    data object Profile : BottomNavigationBar("profile", "Profile", Icons.Default.Person)
    data object Messages : BottomNavigationBar("messages", "Messages", Icons.Default.Email)

    data object Jobs : BottomNavigationBar("careerFieldsView/jobs", "Jobs", Icons.Default.List)

    data object CareerFieldsView : BottomNavigationBar("careerFieldsView/freelancers", "Freelancers", Icons.Default.Face)
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationBar.Jobs,
        BottomNavigationBar.CareerFieldsView,
        BottomNavigationBar.Messages,
        BottomNavigationBar.Profile
    )
    println(items)
    Row {
        Box (
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed() { index, screen ->
                    AddItem(items = screen, navController = navController, index = index)
                }
        }

        }

    }
    }

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddItem(
    items: BottomNavigationBar,
    navController: NavHostController,
    index: Int,
    ) {
        val selected = mutableStateOf(index == indexG)
        val background = if (selected.value) Orange500.copy(alpha = 0.6f) else Color.Transparent

        val contentColor = if (selected.value) Color.White else Color.Black

        Box(
            modifier = Modifier
                .height(40.dp)
                .clip(CircleShape)
                .background(background)
                .clickable(onClick = {
                    navController.navigate(items.route) {
                        indexG = index
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                })
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = items.icon,
                    contentDescription = "icon",
                    tint = contentColor
                )
                AnimatedVisibility(visible = selected.value) {
                    Text(
                        text = items.title,
                        color = contentColor
                    )
                }
            }
        }
    }

var indexG by mutableStateOf(1)

