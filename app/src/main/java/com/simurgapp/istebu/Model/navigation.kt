package com.simurgapp.istebu.Model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.simurgapp.istebu.View.Freelancer.CareerFieldsView
import com.simurgapp.istebu.View.Freelancer.FieldsDetailsView
import com.simurgapp.istebu.View.Freelancer.FreelancersScreen
import com.simurgapp.istebu.View.Jobs.JobsScreen
import com.simurgapp.istebu.View.RegistrationProcedure.LoginScreen
import com.simurgapp.istebu.View.MainScreen
import com.simurgapp.istebu.View.Messages.MessagesScreen
import com.simurgapp.istebu.View.Profile.ProfileView
import com.simurgapp.istebu.View.RegistrationProcedure.SignUpScreen
import com.simurgapp.istebu.View.Freelancer.freelancerDetailsScreen
import com.simurgapp.istebu.View.Jobs.OffersView
import com.simurgapp.istebu.View.Jobs.ProjectView
import com.simurgapp.istebu.View.Jobs.paymentPage
import com.simurgapp.istebu.View.Messages.MessagesDetail
import com.simurgapp.istebu.View.RegistrationProcedure.GetFreelancerInfoView
import com.simurgapp.istebu.View.RegistrationProcedure.GetUserInfoView
import com.simurgapp.istebu.ViewModel.BackViewModel


@Composable
fun AppNav(navController : NavHostController, backViewModel: BackViewModel){

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController , backViewModel) }
        composable("home") { MainScreen(navController , backViewModel) }
        composable("signup") { SignUpScreen() }
        composable("profile") { ProfileView(navController) }
        composable("messages") { MessagesScreen(navController) }
        composable("freelancers") { FreelancersScreen(navController) }
        composable("jobs") { JobsScreen(navController) }
        composable(
            route = "freelancerDetailScreen/{UID}",
            arguments = listOf(
                navArgument("UID") { type = NavType.IntType }


            )
        ) { backStackEntry ->
            val myInt = backStackEntry.arguments?.getInt("UID") ?: 0
            freelancerDetailsScreen(myInt)
        }
        composable("getFreelancerInfoView") { GetFreelancerInfoView() }
        composable("getUserInfoView") { GetUserInfoView() }
        composable("careerFieldsView/{goTo}", arguments = listOf(navArgument("goTo") {type = NavType.StringType})){ backStackEntry ->
            val goTo = backStackEntry.arguments?.getString("goTo") ?: ""
            CareerFieldsView(navController,goTo)}
        composable("fieldsDetailsView/{index}/{goTo}", arguments = listOf(navArgument("index"){type = NavType.IntType}
        ,navArgument("goTo"){type = NavType.StringType}
        )){ backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            val goTo = backStackEntry.arguments?.getString("goTo") ?: ""
            FieldsDetailsView(index,navController,goTo)

        }
        composable("projectView") { ProjectView(navController) }
        composable("offersView") { OffersView(navController) }
        composable("messageDetail"){ MessagesDetail() }
        composable("paymentPage") { paymentPage() }
    }
}