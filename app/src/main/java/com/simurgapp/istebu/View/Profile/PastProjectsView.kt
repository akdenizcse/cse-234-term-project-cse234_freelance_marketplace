package com.simurgapp.istebu.View.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.ViewModel.PastOrOngoingProjectsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.ProjectClass
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.View.UIElements.FilledTonalButton






@Composable
fun PastProjectsView(navController: NavController, viewModel: PastOrOngoingProjectsViewModel = viewModel()) {
    val projects = viewModel.projects.collectAsState().value
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val uid = sharedPreferencesHelper.getUID()



    LaunchedEffect(key1 = viewModel) {
        if (uid != null) {
            viewModel.getProjectsByFreelancersID(uid, {
                println(projects)
            }, {
                println(it)
            })

            viewModel.getProjectsByClientID(uid, {
                println(projects)
            }, {
                println(it)
            })
        }

    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Past Projects",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
        projects.forEach { project ->
            if (project.isFinished) {

                ProjectCardProfile(navController = navController, project = project)
            }
        }

    }

}

@Composable
fun ProjectCardProfile(navController: NavController, project: ProjectClass) {

    val time = project.date.toLong()
    val date = java.util.Date(time)
    val dateStr = date.toString()
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                println("project : ${project.toString()}")
                try {
                    println("project UID : ${project.UID}")
                    navController.navigate("projectView/${project.UID}")
                } catch (e: Exception) {
                    println(e )

                }


            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = project.projectName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = " ${project.projectType}-price",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Posted ${dateStr}")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${project.budget} TL",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = project.experienceLevel + " level experience",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(

                text = if (project.isFinished) "Finished" else "Ongoing",
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                project.skills.forEach { skill ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = skill,
                            fontSize = 12.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            FilledTonalButton(onClick = {  }, text ="See more" )
        }
    }
}