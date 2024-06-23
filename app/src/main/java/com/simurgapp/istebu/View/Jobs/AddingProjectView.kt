package com.simurgapp.istebu.View.Jobs


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.ProjectClass
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import java.util.UUID

@Composable
fun AddingProjectView(navController: NavController) {
    val projectName = remember { mutableStateOf("") }
    val skills = remember { mutableStateListOf<String>() }
    val description = remember { mutableStateOf("") }
    val imageURL = remember { mutableStateListOf<String>() }
    val necessaryBranches = remember { mutableStateListOf<String>() }
    val numberPeople = remember { mutableStateOf("") }
    val budget = remember { mutableStateOf("") }
    val estimatedTime = remember { mutableStateOf("") }
    val projectType = remember { mutableStateOf("") }
    val experienceLevel = remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val currentUserID = sharedPreferencesHelper.getUID() ?: ""
    val firestoreUserRepository = FirestoreUserRepository()
    Column(
        modifier = Modifier
            .padding(16.dp, bottom = 66.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        BasicTextField(
            value = projectName.value,
            onValueChange = { projectName.value = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.1f))
                        .padding(8.dp)
                ) {
                    if (projectName.value.isEmpty()) {
                        Text("Project Name", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        BasicTextField(
            value = description.value,
            onValueChange = { description.value = it },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.1f))
                        .padding(8.dp)
                ) {
                    if (description.value.isEmpty()) {
                        Text("Description", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BasicTextField(
                value = budget.value,
                onValueChange = { budget.value = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        if (budget.value.isEmpty()) {
                            Text("Budget", color = Color.Gray)
                        }
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(bottom = 8.dp)
            )
            BasicTextField(
                value = estimatedTime.value,
                onValueChange = { estimatedTime.value = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        if (estimatedTime.value.isEmpty()) {
                            Text("Estimated Time", color = Color.Gray)
                        }
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(bottom = 8.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            BasicTextField(
                value = projectType.value,
                onValueChange = { projectType.value = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        if (projectType.value.isEmpty()) {
                            Text("Project Type", color = Color.Gray)
                        }
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(bottom = 8.dp)
            )
            BasicTextField(
                value = experienceLevel.value,
                onValueChange = { experienceLevel.value = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        if (experienceLevel.value.isEmpty()) {
                            Text("Experience Level", color = Color.Gray)
                        }
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(bottom = 8.dp)
            )
        }

        Column {
            Text(text = "Skills", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            skills.forEachIndexed { index, skill ->
                Row() {
                    BasicTextField(
                        value = skill,
                        onValueChange = { skills[index] = it },
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .background(Color.Gray.copy(alpha = 0.1f))
                                    .padding(8.dp)
                            ) {
                                if (skill.isEmpty()) {
                                    Text("Skill", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 8.dp)
                    )
                    IconButton(onClick = { skills.removeAt(index) }) {
                        Icon(Icons.Default.Close, contentDescription = "Remove Skill")
                    }
                }
            }
            IconButton(onClick = { skills.add("") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Skill")
            }
        }


        Column {
            Text(text = "Necessary Branches", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            necessaryBranches.forEachIndexed { index, branch ->
                Row() {
                    BasicTextField(
                        value = branch,
                        onValueChange = { necessaryBranches[index] = it },
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .background(Color.Gray.copy(alpha = 0.1f))
                                    .padding(8.dp)
                            ) {
                                if (branch.isEmpty()) {
                                    Text("Branch", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 8.dp)
                    )
                    IconButton(onClick = { necessaryBranches.removeAt(index) }) {
                        Icon(Icons.Default.Close, contentDescription = "Remove Branch")
                    }
                }
            }
            IconButton(onClick = { necessaryBranches.add("") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Branch")
            }
        }


        Column {
            Text(text = "Images", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            imageURL.forEachIndexed { index, url ->
                Row() {
                    BasicTextField(
                        value = url,
                        onValueChange = { imageURL[index] = it },
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .background(Color.Gray.copy(alpha = 0.1f))
                                    .padding(8.dp)
                            ) {
                                if (url.isEmpty()) {
                                    Text("Image URL", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 8.dp)
                    )
                    IconButton(onClick = { imageURL.removeAt(index) }) {
                        Icon(Icons.Default.Close, contentDescription = "Remove Image")
                    }
                }
            }
            IconButton(onClick = { imageURL.add("") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Image")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ){
            com.simurgapp.istebu.View.UIElements.FilledTonalButton(onClick = {
                val project = ProjectClass(
                    UID = UUID.randomUUID().toString(),
                    projectName = projectName.value,
                    clientID = currentUserID,
                    skills = skills.toMutableList(),
                    description = description.value,
                    date = System.currentTimeMillis(),
                    imageURL = imageURL.toMutableList(),
                    necessaryBranches = necessaryBranches.toMutableList(),
                    numberPeople = numberPeople.value.toIntOrNull() ?: 0,
                    budget = budget.value.toFloatOrNull() ?: 0f,
                    isFinished = false,
                    estimatedTime = estimatedTime.value.toIntOrNull() ?: 0,
                    projectType = projectType.value,
                    experienceLevel = experienceLevel.value)
                firestoreUserRepository.addProject(project,{
                    Toast.makeText(context, "Project Added", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },{
                    Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                })
            }, text = "Add Project")
        }

    }
}