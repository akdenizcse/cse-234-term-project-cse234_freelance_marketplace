package com.simurgapp.istebu.View.Jobs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.simurgapp.istebu.Model.OffersClass
import com.simurgapp.istebu.Model.ProjectClass
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.Profile.ProfileInfoItem
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.IconButtonOne
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.ViewModel.JobsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.R
import com.simurgapp.istebu.ViewModel.MessagesViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectView(navController: NavController, projectId: String, viewModel: JobsViewModel = viewModel()) {
    var currentImageIndex = remember { mutableIntStateOf(0) }
    val project = viewModel.project.collectAsState().value
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val uid = sharedPreferencesHelper.getUID()
    val offers = viewModel.offers.collectAsState().value

    LaunchedEffect(projectId) {
        viewModel.getProjectUID(projectId, {
            println("Project data fetched successfully: $it")
        }, {
            println("Error fetching project data: $it")
        })

        viewModel.getOffersByProjectID(projectId, {
            println("Offers fetched successfully: $it")
        }, {
            Toast.makeText(context, "Error fetching offers", Toast.LENGTH_SHORT).show()
        })
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .border(3.dp, Color.Black, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                if (project.imageURL.isNotEmpty()) {
                    projectImage(project.imageURL[currentImageIndex.value])
                } else {
                    Text("No images available", color = Color.Gray)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            IconButtonOne(
                icon = Icons.Default.ArrowBack,
                contentDescription = "previous image",
                onClick = {
                    if (currentImageIndex.value > 0) {
                        currentImageIndex.value -= 1
                    }
                })
            Text(text = "${currentImageIndex.value + 1}/${project.imageURL.size}")
            IconButtonOne(
                icon = Icons.Default.ArrowForward,
                contentDescription = "next image",
                onClick = {
                    if (currentImageIndex.value < project.imageURL.size - 1) {
                        currentImageIndex.value += 1
                    }
                })
        }
        Text(
            text = project.projectName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Date: ${project.date}",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = project.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Skills
        Text(
            text = "Skills Required",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        FlowRow(modifier = Modifier.padding(bottom = 16.dp)) {
            project.skills.forEach { skill ->
                Chip(text = skill)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
        ProfileInfoItem(label = "Budget: ", value = "${project.budget}")
        ProfileInfoItem(label = "Estimated Time", value = "${project.estimatedTime} ")
        ProfileInfoItem(label = "Project Type ", value = project.projectType)
        ProfileInfoItem(label = "Experience Level", value = project.experienceLevel)
        ProfileInfoItem(label = "Necessary Branches", value = project.necessaryBranches.joinToString(", "))
        ProfileInfoItem(label = "Number of People Needed", value = project.numberPeople.toString())
        ProfileInfoItem(label = "Project Status", value = if (project.isFinished) "Finished" else "Ongoing")
        if (project.clientID != uid) {
            if (project.freelancersID.count() < project.numberPeople) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    FilledTonalButton(onClick = { navController.navigate("giveOfferView/$projectId") }, text = "Give offer")
                }
            }
        } else {
            if (!project.isFinished) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilledTonalButton(onClick = {
                        viewModel.updateProjectStatus(projectId, clientID = uid, {
                            navController.popBackStack()
                            Toast.makeText(context, "Project status updated", Toast.LENGTH_SHORT).show()
                        }, {
                            Toast.makeText(context, "Error updating project status", Toast.LENGTH_SHORT).show()
                        })
                        navController.navigate("reviewScreen/${if (project.freelancersID[0].count() > 0) project.freelancersID[0] else ""}")
                    }, text = "Finish")
                }
            }
        }
        OffersArea(offers = offers, navController)
        Spacer(modifier = Modifier.height(64.dp))
    }
}


@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = text)
    }
}
@Composable
fun projectImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                listener(
                    onSuccess = { _, _ -> println("Image loaded successfully: $imageUrl") },
                    onError = { _, _ -> println("Error loading image: $imageUrl") }
                )
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}
@Composable
fun OffersArea(offers: List<OffersClass> , navController: NavController) {
    println("Offers: $offers")
    Column {
        Text(
            text = "Offers",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        offers.forEach { offer ->
            OfferItem(offer = offer,navController)
        }
    }
}
@Composable
fun OfferItem(offer: OffersClass , navController: NavController , viewModel: MessagesViewModel = viewModel()) {
    val freelancerName = remember {
        mutableStateOf("isim girilmedi")
    }
    val freelancerImage = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = offer.freelancerID) {
        viewModel.getUserByUID(offer.freelancerID,
            onSuccess = { user ->
                freelancerName.value = user.name
                freelancerImage.value = user.imageURL
            },
            onFailure = { exception -> println("Error fetching user by UID: ${exception.message}") }
        )
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("offersView")
            }
            .padding(16.dp)
    ) {
        CircleImage(imageUrl = freelancerImage.value, modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = freelancerName.value,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Price: $${offer.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Completion Date: ${offer.estimatedTime} ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Comments: ${offer.comment}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}