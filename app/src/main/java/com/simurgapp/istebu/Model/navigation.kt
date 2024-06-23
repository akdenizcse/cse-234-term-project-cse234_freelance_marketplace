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
import com.simurgapp.istebu.View.Jobs.AddingProjectView
import com.simurgapp.istebu.View.Jobs.OffersView
import com.simurgapp.istebu.View.Jobs.ProjectView
import com.simurgapp.istebu.View.Jobs.paymentPage
import com.simurgapp.istebu.View.Messages.MessagesDetail
import com.simurgapp.istebu.View.Profile.OngoingProjectsView
import com.simurgapp.istebu.View.Profile.PastProjectsView
import com.simurgapp.istebu.View.RegistrationProcedure.GetFreelancerInfoView
import com.simurgapp.istebu.View.RegistrationProcedure.GetUserInfoView
import com.simurgapp.istebu.View.ReviewScreen
import com.simurgapp.istebu.ViewModel.BackViewModel


@Composable
fun AppNav(navController : NavHostController, backViewModel: BackViewModel){

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController , backViewModel) }
        composable("home") { MainScreen(navController , backViewModel) }
        composable("signup") { SignUpScreen(navController) }
        composable("profile") { ProfileView(navController) }
        composable("messages") { MessagesScreen(navController) }
        composable("freelancers/{cField}" , arguments = listOf(navArgument("cField"){type = NavType.StringType})) { backStackEntry ->

            println("gridi 1")
            val cField = backStackEntry.arguments?.getString("cField") ?: ""
            println("gridi 2")
            FreelancersScreen(navController,cField) }
        composable("jobs/{cField}", arguments = listOf(navArgument("cField"){type = NavType.StringType})) { bse ->
            val cField = bse.arguments?.getString("cField") ?: ""
            JobsScreen(navController ,cField) }
        composable(
            route = "freelancerDetailScreen/{UID}",
            arguments = listOf(
                navArgument("UID") { type = NavType.StringType }


            )
        ) { backStackEntry ->
            val myInt = backStackEntry.arguments?.getString("UID") ?: ""
            freelancerDetailsScreen(myInt, navController)
        }
        composable("getFreelancerInfoView") { GetFreelancerInfoView(navController) }
        composable("getUserInfoView/{cameF}", arguments = listOf(navArgument("cameF"){type = NavType.BoolType})) { backStackEntry ->
            val cameF = backStackEntry.arguments?.getBoolean("cameF") ?: false
            GetUserInfoView(navController,cameF) }
        composable("careerFieldsView/{goTo}", arguments = listOf(navArgument("goTo") {type = NavType.StringType})){ backStackEntry ->
            val goTo = backStackEntry.arguments?.getString("goTo") ?: ""
            CareerFieldsView(navController,goTo)}
        composable("fieldsDetailsView/{index}/{goTo}", arguments = listOf(navArgument("index"){type = NavType.IntType}
            ,navArgument("goTo"){type = NavType.StringType}
        )){ backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            val goTo = backStackEntry.arguments?.getString("goTo") ?: ""
            println("gotoooooooo"+goTo)
            FieldsDetailsView(index,navController,goTo)

        }
        composable("projectView") { ProjectView(navController) }
        composable("offersView") { OffersView(navController) }
        composable("messageDetail/{chatID}/{senderId}/{reciverId}" , arguments = listOf(navArgument("chatID"){type = NavType.StringType})){ backStackEntry ->
            val chatID = backStackEntry.arguments?.getString("chatID") ?: ""
            val senderId = backStackEntry.arguments?.getString("senderId") ?: ""
            val reciverId = backStackEntry.arguments?.getString("reciverId") ?: ""
            MessagesDetail(chatID,senderId,reciverId) }
        composable("paymentPage") { paymentPage() }
        composable("reviewScreen") { ReviewScreen() }
        composable("addingProjectView"){ AddingProjectView(navController)}
        composable("pastProjectsView"){ PastProjectsView(navController)}
        composable("ongoingProjectsView") {OngoingProjectsView(navController)}
    }
}