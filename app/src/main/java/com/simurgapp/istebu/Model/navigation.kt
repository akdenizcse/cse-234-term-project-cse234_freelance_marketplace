package com.simurgapp.istebu.Model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simurgapp.istebu.View.FreelancersScreen
import com.simurgapp.istebu.View.JobsScreen
import com.simurgapp.istebu.View.LoginScreen
import com.simurgapp.istebu.View.MainScreen
import com.simurgapp.istebu.View.MessagesScreen
import com.simurgapp.istebu.View.ProfileScreen
import com.simurgapp.istebu.View.SignUpScreen
import com.simurgapp.istebu.ViewModel.BackViewModel

@Composable
fun AppNav(navController : NavHostController, backViewModel: BackViewModel){

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController , backViewModel) }
        composable("home") { MainScreen(navController , backViewModel) }
        composable("signup") { SignUpScreen() }
        composable("profile") { ProfileScreen() }
        composable("messages") { MessagesScreen() }
        composable("freelancers") { FreelancersScreen() }
        composable("jobs") { JobsScreen() }
    }
}