package com.simurgapp.istebu.View.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.ViewModel.PastOrOngoingProjectsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.SharedPreferencesHelper

@Composable
fun OngoingProjectsView(
    navController: NavController,
    viewModel: PastOrOngoingProjectsViewModel = viewModel()
) {
    val projects = viewModel.projects.collectAsState().value
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val uid = sharedPreferencesHelper.getUID()

    LaunchedEffect(key1 = viewModel) {
        if (uid != null) {
            viewModel.resetProjects()
            viewModel.getProjectsByFreelancersID(uid, { println(projects) }, { println(it) })
            viewModel.getProjectsByClientID(uid, { println(projects) }, { println(it) })
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Ongoing Projects",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        projects.forEach { project ->
            if (!project.isFinished) {
                ProjectCardProfile(navController = navController, project = project)
            }
        }
    }
}
