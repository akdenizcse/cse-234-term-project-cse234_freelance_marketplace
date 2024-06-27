package com.simurgapp.istebu.View.Freelancer
import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.toObject
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.View.UIElements.GenericListView
import com.simurgapp.istebu.View.UIElements.PicTextItem
import com.simurgapp.istebu.Model.FirestoreUserRepository

@Composable
fun FreelancersScreen(navController: NavController, subBranch: String) {

    val freelancers = remember { mutableStateListOf<FreelancerClass>() }
    val firestoreUserRepository = FirestoreUserRepository()


    LaunchedEffect(subBranch) {
        firestoreUserRepository.getFreelancersBYSubBranches(subBranch, { documents ->
            println("subBranch : $subBranch")
            val freelancerList = documents.mapNotNull { doc ->
                doc.toObject<FreelancerClass>()
            }
            freelancers.clear()
            freelancers.addAll(freelancerList)
        }, { exception ->
            Log.d(ContentValues.TAG, "Failed to get freelancers: ", exception)
        })
    }

    Box(modifier = Modifier.padding(bottom = 64.dp)) {
        GenericListView(freelancers) { item, index ->
            var imageUrl = remember {
                mutableStateOf("")
            }
            firestoreUserRepository.getImageForUser(item.UID, { url ->
                imageUrl.value = url.toString()
            }, { exception ->
                Log.d(ContentValues.TAG, "Failed to get image: ", exception)
            })
            PicTextItem(
                freelancers[index].UID,
                freelancers[index].name,
                subBranch,
                imageUrl = imageUrl.value
            ) {
                navController.navigate("freelancerDetailScreen/${item.UID}")
            }
        }
    }
}