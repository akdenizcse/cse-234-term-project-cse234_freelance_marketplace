package com.simurgapp.istebu.ViewModel

import androidx.lifecycle.ViewModel
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.OffersClass
import com.simurgapp.istebu.Model.ProjectClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class JobsViewModel : ViewModel() {

    val firestoreUserRepository = FirestoreUserRepository()
    private val _projects = MutableStateFlow<List<ProjectClass>>(emptyList())
    val projects : StateFlow<List<ProjectClass> > = _projects

    private val _project = MutableStateFlow<ProjectClass>(ProjectClass())
    val project : StateFlow<ProjectClass> = _project

    private val _offers = MutableStateFlow<List<OffersClass>>(emptyList())
    val offers : StateFlow<List<OffersClass> > = _offers


    fun getProjects(branch : String, onSuccess:(List<ProjectClass>) -> Unit,onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getProjectsByNecessaryBranches(branch, {
            _projects.value = it
            onSuccess(it)
        }, onFailure)
    }
    fun getProjectUID(projectID: String, onSuccess: (ProjectClass) -> Unit, onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getProjectByUID(projectID, {
            _project.value = it
            onSuccess(it)
        }, onFailure)
    }


    fun  updateProjectStatus(projectID: String,clientID : String ,onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val pOViewModel = PastOrOngoingProjectsViewModel()
        firestoreUserRepository.updateProjectStatusToFinished(projectID, {
            pOViewModel.resetProjects()
            pOViewModel.getProjectsByClientID(clientID, {
            }, {
            })
            pOViewModel.getProjectsByFreelancersID(clientID, {
            }, {
            })
            onSuccess()

        }, onFailure)

    }

    fun giveOffer(projectID: String, freelancerID: String, price: Int, estimatedTime: String, comment: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val offer = OffersClass(
            UID = UUID.randomUUID().toString(),
            price = price,
            estimatedTime = estimatedTime,
            projectID = projectID,
            freelancerID = freelancerID,
            date = System.currentTimeMillis().toString(),
            isAccepted = false,
            isRejected = false,
            isFinished = false,
            comment = comment
        )
        firestoreUserRepository.addOffer(offer, {
            onSuccess()
        }, onFailure)
    }

    fun getOffersByProjectID(projectID: String, onSuccess: (List<OffersClass>) -> Unit, onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getOffersByProjectId(projectID, {
            _offers.value = it
            onSuccess(it)
        }, onFailure)
    }

}