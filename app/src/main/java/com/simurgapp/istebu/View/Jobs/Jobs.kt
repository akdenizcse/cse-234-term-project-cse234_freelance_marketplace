package com.simurgapp.istebu.View.Jobs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ChipDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.ProjectClass
import com.simurgapp.istebu.ViewModel.JobsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobsScreen(navController: NavController, field: String, viewModel: JobsViewModel = viewModel()) {
    println("Job Screen çalıştı")

    val projectList = viewModel.projects.collectAsState().value

    LaunchedEffect(key1 = field) {
        viewModel.getProjects(field, {
            println(projectList)
        }, {
            Toast.makeText(navController.context, it.toString(), Toast.LENGTH_SHORT).show()
        })
        println("veriler çekildi $field")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, bottom = 66.dp)
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            for (project in projectList) {
                ProjectCard(navController, project)
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("addingProjectView") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Project")
        }
    }
}

@Composable
fun ProjectCard(navController: NavController, project: ProjectClass) {

    // time için milisaniyeyi tarihe çevir
    val time = project.date.toLong()
    val date = java.util.Date(time)
    val dateStr = date.toString()
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {

                navController.navigate("projectView/${project.UID}")
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
                text = project.description,
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
        }
    }


}