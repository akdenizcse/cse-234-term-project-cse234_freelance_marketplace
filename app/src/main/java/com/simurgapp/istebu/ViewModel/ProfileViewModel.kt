package com.simurgapp.istebu.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.toObject
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.FreelancerClass
import com.simurgapp.istebu.Model.ProjectClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ProfileViewModel : ViewModel() {
    private val firestoreUserRepository = FirestoreUserRepository()
    private val _user = MutableStateFlow<FreelancerClass?>(null)
    val user: StateFlow<FreelancerClass?> get() = _user

    private val _pastProjects = MutableStateFlow<List<ProjectClass>>(emptyList())
    val pastProjects: StateFlow<List<ProjectClass>> get() = _pastProjects

    private val _onGoingProjects = MutableStateFlow<List<ProjectClass>>(emptyList())
    val onGoingProjects: StateFlow<List<ProjectClass>> get() = _onGoingProjects

    private val _isFreelancer = MutableStateFlow<Boolean>(false)
    val isFreelancer: StateFlow<Boolean> get() = _isFreelancer

    private val _isCompleted = MutableStateFlow<Boolean>(false)
    val isCompleted: StateFlow<Boolean> get() = _isCompleted

    private val _imageUrl = MutableStateFlow<Uri?>(null)
    val imageUrl: StateFlow<Uri?> get() = _imageUrl
    fun getUserData(uid: String, onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getFreelancerByUID(uid, { document ->
            _user.value = document.toObject<FreelancerClass>()
        }, { exception ->
            println("Failed to get freelancer: $exception")
            onFailure(exception)
        })
    }

    private suspend fun getProjectsByClientID(freelancerID: String): List<ProjectClass> =
        suspendCancellableCoroutine { continuation ->
            firestoreUserRepository.getProjectsByClientID(freelancerID, { projects ->
                continuation.resume(projects)
            }, { exception ->
                continuation.resumeWithException(exception)
            })
        }

    private suspend fun getProjectsFreelancersID(freelancerID: String): List<ProjectClass> =
        suspendCancellableCoroutine { continuation ->
            firestoreUserRepository.getProjectsFreelancersID(freelancerID, { projects ->
                continuation.resume(projects)
            }, { exception ->
                continuation.resumeWithException(exception)
            })
        }

    fun getProjects(freelancerID: String, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val clientProjectsDeferred = async {
                    getProjectsByClientID(freelancerID)
                }
                val freelancerProjectsDeferred = async {
                    getProjectsFreelancersID(freelancerID)
                }

                val clientProjects = clientProjectsDeferred.await()
                val freelancerProjects = freelancerProjectsDeferred.await()

                val allProjects = clientProjects + freelancerProjects
                _onGoingProjects.value = allProjects.filter { !it.isFinished }
                _pastProjects.value = allProjects.filter { it.isFinished }
            } catch (exception: Exception) {
                println("Failed to get projects: $exception")
                onFailure(exception)
            }
        }
    }

    fun getPastProjectsByFreelancersID(freelancerID: String, onSuccess: (List<ProjectClass>) -> Unit, onFailure: (Exception) -> Unit) {
        firestoreUserRepository.getProjectsFreelancersID(
            freelancerID, {

                _pastProjects.value = it
                onSuccess(it)
            }, onFailure
        )
    }

    fun resetPastAndOngoingProjects() {
        _pastProjects.value = emptyList()
        _onGoingProjects.value = emptyList()
    }
    fun getIsFreelancer(uid :String){
        firestoreUserRepository.getFreelancerByUID(uid, { document ->
            val freelancer = document.toObject<FreelancerClass>()
            if (freelancer != null) {
                if (freelancer.UID != "") {
                    _isFreelancer.value = true
                }
            }


            println("is freelancer: ${_isFreelancer.value}")
        }, { exception ->
            println("Failed to get freelancer: $exception")
        })

    }
    fun getIsCompleted(uid: String){
        firestoreUserRepository.getUserByUID(
            UID = uid,
            onSuccess = { user ->
                if (user != null) {
                    _isCompleted.value = true
                }
                println("is completed: ${_isCompleted.value}")
            },
            onFailure = { exception ->
                println("Failed to get user: $exception")
            }
        )
    }
    fun addImage(uid: String, imageUrl: Uri) {
        firestoreUserRepository.uploadImageForUser(uid, imageUrl, {
            _imageUrl.value = it
        }, {
            println("Failed to upload image: $it")
        })
    }
    fun getImage(uid: String) {
        firestoreUserRepository.getImageForUser(uid, {
            _imageUrl.value = it
        }, {
            println("Failed to get image: $it")
        })
    }

}