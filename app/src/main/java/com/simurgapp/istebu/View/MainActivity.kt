package com.simurgapp.istebu.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.simurgapp.istebu.ui.theme.IsteBuTheme
import androidx.navigation.compose.rememberNavController
import com.simurgapp.istebu.Model.AppNav
import com.simurgapp.istebu.View.UIElements.BottomBar
import com.simurgapp.istebu.View.UIElements.MyTopAppBar
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

    Scaffold(
        topBar = {
            MyTopAppBar(navController = navController , backViewModel)
        },
        bottomBar = { 
                    BottomBar(navController = navController)
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




