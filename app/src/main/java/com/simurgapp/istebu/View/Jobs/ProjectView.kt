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
import com.simurgapp.istebu.View.UIElements.CommentsArea
import com.simurgapp.istebu.View.UIElements.IconButtonOne
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.ViewModel.JobsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simurgapp.istebu.Model.SharedPreferencesHelper


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectView (navController: NavController,projectId: String , viewModel: JobsViewModel = viewModel())
{

    var currentImageIndex = remember { mutableIntStateOf(0) }
    val project : ProjectClass = viewModel.project.collectAsState().value
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val uid = sharedPreferencesHelper.getUID()

    LaunchedEffect(key1 = projectId) {
        viewModel.getProject(projectId, {
            println(project)
            println("finished status ${project.isFinished}")

        }, {
            println(it)
        })

    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()


    ) {
        if (project.imageURL.isEmpty()) {
            project.imageURL.add("https://picsum.photos/200/300")
        }

        Box(modifier = Modifier
            .border(3.dp, Color.Black, RoundedCornerShape(8.dp))
            .fillMaxWidth()
        ){
            Row(modifier = Modifier
                .padding(16.dp)) {

                projectImage(project.imageURL[currentImageIndex.value])

            }
        }
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
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


                    if( currentImageIndex.value < project.imageURL.size-1)
                        currentImageIndex.value += 1
                    println(    "Current Index"+currentImageIndex.value)
                }
            )
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
        FlowRow(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            project.skills.forEach { skill ->
                Chip(text = skill)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
        ProfileInfoItem(label = "Budget: ", value = "${project.budget}")
        ProfileInfoItem(label = "Estimeted Time", value = "${project.estimatedTime} days")
        ProfileInfoItem(label = "Project Type ", value = project.projectType)
        ProfileInfoItem(label = "Experience Level", value = project.experienceLevel)
        ProfileInfoItem(label = "Necessary Branches", value = project.necessaryBranches.joinToString(", "))
        ProfileInfoItem(label = "Number of People Needed", value =project.numberPeople.toString())
        ProfileInfoItem(label = "Project Status", value = if (project.isFinished) "Finished" else "Ongoing")
        if (project.clientID != uid) {
            if (project.freelancersID.count() < project.numberPeople) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    FilledTonalButton(onClick = { /*TODO: Give offer action */ }, text = "Give offer")
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
                            Toast.makeText(context, "Project status updated", Toast.LENGTH_SHORT)
                                .show()
                        }, {
                            Toast.makeText(
                                context,
                                "Error updating project status",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        })
                    }, text = "Finish")
                }
            }
        }



        OffersArea(offers = project.offers , navController)
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
        painter = rememberImagePainter(data = imageUrl),
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
fun OfferItem(offer: OffersClass , navController: NavController) {
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
        CircleImage(imageUrl = tempData().images.random(), modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Freelancer Name",
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
                text = "Estimated Time: ${offer.estimatedTime} days",
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