package com.simurgapp.istebu.View.Jobs


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.LocalAtm
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
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.RegistrationProcedure.SelectedFieldsView
import com.simurgapp.istebu.View.UIElements.Counter
import com.simurgapp.istebu.View.UIElements.DatePicker
import com.simurgapp.istebu.View.UIElements.DropDown
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.SelectableDropDown
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import java.util.UUID

@Composable
fun AddingProjectView(navController: NavController) {
    val projectName = remember { mutableStateOf("") }
    val skills = remember { mutableStateListOf<String>("") }
    val description = remember { mutableStateOf("") }
    val imageURL = remember { mutableStateListOf<String>() }
    val numberPeople = remember { mutableStateOf("") }
    val budget = remember { mutableStateOf("") }
    val estimatedTime = remember { mutableStateOf("") }
    val projectType = remember { mutableStateOf("") }
    val experienceLevel = remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val currentUserID = sharedPreferencesHelper.getUID() ?: ""
    val firestoreUserRepository = FirestoreUserRepository()
    val projectTypeList = listOf("Hourly", "Fixed")
    val experienceLevelList = listOf("Beginner", "Intermediate", "Advanced")
    val tempData = tempData()
    val UID = UUID.randomUUID().toString()
    val selectedImagesUrl = remember { mutableStateListOf<Uri>() }

    val careerFields = listOf(
        "Graphic Design",
        "Programming",
        "Translation",
        "Teaching",
        "Fitness Training",
        "UI/UX Design",
        "Video Editing",
        "Voiceover",
        "Life Coaching",
        "Nutrition Coach",
        "Photoshop",
        "Illustration",
        "Animation",
        "Social Media Management",
        "Marketing",
        "Copywriting",
        "Accountant"
    )

    val selectedFields = remember { mutableStateListOf<String>() }
    var subBranches = remember { mutableStateListOf<String>() }
    val selectedSubBranch = remember { mutableStateListOf<String>() }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris: List<Uri> ->
        selectedImagesUrl.clear()
        selectedImagesUrl.addAll(uris)
    }

    Column(
        modifier = Modifier
            .padding(16.dp, bottom = 66.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextFieldOne(labelText = "Project Name", leadingIconOne = Icons.Default.BorderColor , colorOne = Orange200 , colorTwo =  darkerOrange, text = projectName )
        Spacer(modifier = Modifier.height(8.dp) )
        TextFieldOne(labelText = "Description", leadingIconOne = Icons.Default.Comment , colorOne = Orange200 , colorTwo =  darkerOrange, text = description )
        Spacer(modifier = Modifier.height(8.dp) )
        TextFieldOne(labelText = "Budget", leadingIconOne = Icons.Default.LocalAtm , colorOne = Orange200 , colorTwo =  darkerOrange, text = budget )
        Spacer(modifier = Modifier.height(8.dp) )
        DatePicker { estimatedTime.value = it }
        Spacer(modifier = Modifier.height(8.dp) )
        DropDown(list = projectTypeList,  onSelected = { projectType.value = projectTypeList[it] })
        Spacer(modifier = Modifier.height(4.dp) )
        DropDown(list = experienceLevelList,  onSelected = { experienceLevel.value = experienceLevelList[it] })
        Spacer(modifier = Modifier.height(8.dp) )
        SelectableDropDown(
            careerFields = careerFields,
            selectedIndices = selectedFields,
            text = "Career Fields",
            onItemSelected = { index ->
                if (selectedFields.contains(careerFields[index])) {
                    selectedFields.remove(careerFields[index])
                } else {
                    selectedFields.add(careerFields[index])
                    subBranches += tempData.careerFields[index].subList(2, tempData().careerFields[index].size)
                }
            }
        )
        SelectedFieldsView(selectedFields.sortedBy { str ->
            str.length
        }) { field ->
            selectedFields.remove(field)
            careerFields.indexOf(field).let {
                subBranches.removeAll(tempData.careerFields[it].subList(2, tempData().careerFields[it].size))
                selectedSubBranch.removeAll(tempData.careerFields[it].subList(2, tempData().careerFields[it].size))
            }

        }
        Spacer(modifier =Modifier.height(4.dp))
        SelectableDropDown(careerFields = subBranches , selectedIndices = selectedSubBranch,"Sub Branch") { index ->
            if (selectedSubBranch.contains(subBranches[index])) {
                selectedSubBranch.remove(subBranches[index])
            } else {
                selectedSubBranch.add(subBranches[index])
            }
        }
        Spacer(modifier =Modifier.height(4.dp))

        SelectedFieldsView(selectedFields = selectedSubBranch.sortedBy { str ->
            str.length
        }){ field ->
            selectedSubBranch.remove(field)

        }
        Counter ({ numberPeople.value = it.toString() },"Number of People" )






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





        FilledTonalButton(onClick = {
            launcher.launch("image/*")
        }, text = "Add Image")

        Spacer(modifier = Modifier.height(16.dp))
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ){
            com.simurgapp.istebu.View.UIElements.FilledTonalButton(onClick = {
                println("basıldııııı")
                val project = ProjectClass(
                    UID = UID,
                    projectName = projectName.value,
                    clientID = currentUserID,
                    skills = skills.toMutableList(),
                    description = description.value,
                    date = System.currentTimeMillis(),
                    imageURL = (selectedImagesUrl.map { it.toString() }).toMutableList(),
                    necessaryBranches = selectedSubBranch.toMutableList(),
                    numberPeople = numberPeople.value.toIntOrNull() ?: 0,
                    budget = budget.value.toFloatOrNull() ?: 0f,
                    isFinished = false,
                    estimatedTime = estimatedTime.value,
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