package com.simurgapp.istebu.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.simurgapp.istebu.ui.theme.IsteBuTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



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
fun MainActivityContent(navController: NavHostController){
    Scaffold(
        topBar = {
            MyTopAppBar(navController = navController)
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNav(navController = navController)
            }
        }
    )

}
@Composable
fun AppNav(navController : NavHostController){
    NavHost(navController = navController, startDestination = "home") {
        composable("login") { LoginScreen(navController) }
        composable("home") { MainScreen(navController)}
        composable("signup") { SignUpScreen() }
    }
}
@Composable
fun MainScreen (navHostController: NavHostController) {
    var isLogin : Boolean = false

    if (isLogin) {
        Greeting("deneme")
    }
    else {
       LoginScreen(navHostController)
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "deneme2 $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IsteBuTheme {
        Greeting("deneme3 ")
    }
}

@Composable
fun MyTopAppBar(navController: NavHostController) {
    var isBack = navController.currentBackStackEntry.toString()

    TopAppBar(
        backgroundColor = Color(255, 165, 0),
        contentColor = Color.Black,
        title = { Text(text = "İşteBu" , fontSize = 24.sp , textAlign = TextAlign.Center) },

        navigationIcon = {
                IconButton(onClick = {
                    println(isBack)
                    navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }


        },
        actions = {

        }
    )
}