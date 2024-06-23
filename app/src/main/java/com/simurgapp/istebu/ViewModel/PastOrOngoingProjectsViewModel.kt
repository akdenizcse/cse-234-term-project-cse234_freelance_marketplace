package com.simurgapp.istebu.ViewModel

import androidx.lifecycle.ViewModel
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.ProjectClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PastOrOngoingProjectsViewModel : ViewModel() {
    val firestoreUserRepository = FirestoreUserRepository()
    private val _projects = MutableStateFlow<List<ProjectClass>>(emptyList())
    val projects : StateFlow<List<ProjectClass>> = _projects


    fun getProjectsByFreelancersID(freelancerID : String, onSuccess:(List<ProjectClass>) -> Unit,onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getProjectsFreelancersID(
            freelancerID, {
                _projects.value += it
                onSuccess(it)
            }, onFailure
        )
    }
    fun getProjectsByClientID(clientID : String, onSuccess:(List<ProjectClass>) -> Unit,onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getProjectsByClientID(
            clientID, {
                _projects.value += it
                onSuccess(it)
            }, onFailure
        )
    }

}