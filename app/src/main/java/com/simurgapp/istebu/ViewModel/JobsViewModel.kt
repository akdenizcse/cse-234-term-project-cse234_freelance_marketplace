package com.simurgapp.istebu.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.common.api.Api.Client
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.ProjectClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JobsViewModel : ViewModel() {

    val firestoreUserRepository = FirestoreUserRepository()
    private val _projects = MutableStateFlow<List<ProjectClass>>(emptyList())
    val projects : StateFlow<List<ProjectClass> > = _projects

    private val _project = MutableStateFlow<ProjectClass>(ProjectClass())
    val project : StateFlow<ProjectClass> = _project


    fun getProjects(branch : String, onSuccess:(List<ProjectClass>) -> Unit,onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getProjectsByNecessaryBranches(branch, {
            _projects.value = it
            onSuccess(it)
        }, onFailure)
    }
    fun getProject(projectID : String, onSuccess:(ProjectClass) -> Unit,onFailure: (Exception) -> Unit) {
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

}