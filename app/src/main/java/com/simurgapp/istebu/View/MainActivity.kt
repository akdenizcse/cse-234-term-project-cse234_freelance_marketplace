package com.simurgapp.istebu.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.simurgapp.istebu.ui.theme.IsteBuTheme
import androidx.navigation.compose.rememberNavController
import com.simurgapp.istebu.Model.AppNav
import com.simurgapp.istebu.View.UIElements.BottomBar
import com.simurgapp.istebu.View.UIElements.MyTopAppBar
import com.simurgapp.istebu.View.UIElements.indexG
import com.simurgapp.istebu.ViewModel.BackViewModel


class MainActivity : ComponentActivity() {
private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            IsteBuTheme {

                MainActivityContent(navController =navController)

            }
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainActivityContent(navController: NavHostController ){
    val backViewModel = remember { BackViewModel() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    when (currentRoute) {
        "careerFieldsView/jobs" -> indexG = 0
        "careerFieldsView/freelancers" -> indexG = 1
        "messages" -> indexG = 2
        "profile" -> indexG = 3
    }


    Scaffold(
        topBar = {
            if (currentRoute != null) {
                if (!currentRoute.contains("messageDetail"))
                    MyTopAppBar(navController = navController , backViewModel)
            }
        },
        bottomBar = {
            if (currentRoute != "login" && currentRoute != "signup") {
                BottomBar(navController = navController)
            }
        }
        ,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNav(navController = navController, backViewModel = backViewModel)
            }
        }
    )

}

@Composable
fun MainScreen (navHostController: NavHostController , backViewModel: BackViewModel) {
        HomeScreen()
}




