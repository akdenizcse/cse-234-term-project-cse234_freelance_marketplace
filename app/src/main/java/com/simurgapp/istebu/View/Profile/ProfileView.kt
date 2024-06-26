package com.simurgapp.istebu.View.Profile

import LoginsigninViewModel
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.CommentsArea
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simurgapp.istebu.Model.ProjectClass
import com.simurgapp.istebu.ViewModel.ProfileViewModel
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.material.IconButton



@Composable
fun ProfileView(navController: NavController , viewModel: ProfileViewModel = viewModel()) {
    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    val userID = sharedPreferencesHelper.getUID() ?: ""
    val loginViewModel = LoginsigninViewModel(sharedPreferencesHelper)
    var tempData = tempData()
    val imageSize = 200
    val onGoingProjects = viewModel.onGoingProjects.collectAsState().value
    val pastProjects = viewModel.pastProjects.collectAsState().value

    val isUserCreated by viewModel.isCompleted.collectAsState()
    val isFreelancerCreated by viewModel.isFreelancer.collectAsState()

    val currentUser = viewModel.user.collectAsState().value ?: FreelancerClass(
        name = "Name",
        surname = "Surname",
        email = "email",
        phoneNumber = "phone",
        education = "education",
        country = "country",
        city = "city",
        careerFields = mutableListOf("fields"),
        pastProjects = mutableListOf("Past Projects"),
        ongoingProjects = mutableListOf("Ongoing Projects"),
        rating = 4.5f,
        comments = mutableListOf("comments"),
        imageURL = "https://picsum.photos/200/300"
    )
    LaunchedEffect(viewModel) {
        viewModel.resetPastAndOngoingProjects()
        viewModel.getIsCompleted(userID)
        viewModel.getIsFreelancer(userID)
        viewModel.getUserData(userID,
            onFailure = { exception ->
                Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
            })
        viewModel.getProjects(userID,
            onFailure = { exception ->
                Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
            })
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ProfileImageSection(
                tempData = tempData,
                imageSize = imageSize,
                viewModel = viewModel,
                userID = userID
            )



            Text(
                text = "${currentUser.name} ${currentUser.surname}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                //.border(2.dp, Color(0xFF006400), RoundedCornerShape(12.dp))

            )
            Text(
                text = currentUser.careerFields.joinToString(", "),
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(12.dp))

            PastProjectsSection(
                pastProjects = pastProjects,
                "Past Projects",
                { navController.navigate("pastProjectsView") })
            PastProjectsSection(
                pastProjects = onGoingProjects,
                "Ongoing Projects",
                { navController.navigate("ongoingProjectsView") })
            Spacer(modifier = Modifier.height(16.dp))
            RatingSection(rating = currentUser.rating)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                ProfileInfoSection(currentUser = currentUser)
                Spacer(modifier = Modifier.width(200.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (!isFreelancerCreated) {

                FilledTonalButton(
                    onClick = {
                        if (isUserCreated) {
                            navController.navigate("getFreelancerInfoView")
                        } else {
                            val cameF = true
                            navController.navigate("getUserInfoView/${cameF}")
                        }
                    },
                    text = "Freelancer Olmak İstiyorum"
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
            if (!isUserCreated) {
                FilledTonalButton(
                    onClick = {
                        var cameF = false
                        navController.navigate("getUserInfoView/${cameF}")
                    },
                    text = "Üyeliği Tamamla"
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Spacer(modifier = Modifier.height(16.dp))
            }
            FilledTonalButton(
                onClick = {
                    sharedPreferencesHelper.clear()
                    while (navController.popBackStack()) {

                    }
                    navController.navigate("login")
                },
                text = "Log Out"
            )


            CommentsArea(comments = currentUser.comments){
                navController.navigate("commentsView")
            }


            Spacer(modifier = Modifier.height(64.dp))


        }
    }
}



@Composable
fun ProfileImageSection(
    tempData: tempData,
    imageSize: Int,
    viewModel: ProfileViewModel = viewModel(),
    userID : String

) {
    var selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val imageUri = viewModel.imageUrl.collectAsState()
    LaunchedEffect(key1 = viewModel) {
        viewModel.getImage(userID)

    }
    Box {
        if (imageUri.value != null) {
            CircleImage(
                imageUrl = imageUri.value.toString(),
                size = imageSize,
                modifier = Modifier
                    .size(imageSize.dp)
                    .clip(CircleShape)
            )
        } else {
            CircleImage(
                imageUrl = tempData.images.random(),
                size = imageSize,
                modifier = Modifier
                    .size(imageSize.dp)
                    .clip(CircleShape)
            )
        }

        val onImageSelectedLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                selectedImageUri?.let {
                    viewModel.addImage(userID,selectedImageUri)
                }
            }
        }

        IconButton(
            onClick = {
                val galleryIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryIntent.type = "image/*"
                galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))

                onImageSelectedLauncher.launch(galleryIntent)
            },
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-imageSize / 12).dp, y = (imageSize / -12).dp)
                .background(Color.LightGray, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Add Image",
                tint = Color.Black
            )
        }
    }
}
@Composable
fun ProfileInfoSection(currentUser: FreelancerClass) {
    Column {

        Spacer(modifier = Modifier.size(8.dp))
        ProfileInfoItem(label = "Email", value = currentUser.email)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "Phone", value = currentUser.phoneNumber)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "Education", value = currentUser.education)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "Country", value =currentUser.country )
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        ProfileInfoItem(label = "City", value = currentUser.city)
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}


@Composable
fun RatingSection(rating: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Rating:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = if (i <= rating.toInt()) Color(0xFFFFBF00) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$rating/5", fontSize = 16.sp)
    }
}
@Composable
fun PastProjectsSection(pastProjects: List<ProjectClass> ,title : String = "Past Projects" , onClick : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            IconButton(
                onClick = { onClick()},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Show all")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            pastProjects.forEach { project ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(150.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF2C4E80),
                                        Color(0xFF00215E)
                                    )
                                )
                            )
                    ) {
                        Text(
                            text = project.projectName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}