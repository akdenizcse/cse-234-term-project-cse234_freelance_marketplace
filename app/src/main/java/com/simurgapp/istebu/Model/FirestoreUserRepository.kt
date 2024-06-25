package com.simurgapp.istebu.Model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class FirestoreUserRepository {

    val db = Firebase.firestore



    fun addFreelancer(
        UID: String,
        education: String,
        careerFields: MutableList<String>,
        subBranches: MutableList<String>   ,
        definition: String,
        dailyPrice: Int,
        OnSuccess: () -> Unit
    ) {

        getUserByUID(UID, { document ->
            val name = document.getString("name") ?: ""
            val surname = document.getString("surname") ?: ""
            val country = document.getString("country") ?: ""
            val city = document.getString("city") ?: ""
            val email = document.getString("email") ?: ""
            val phoneNumber = document.getString("phoneNumber") ?: ""
            val job = document.getString("job") ?: ""


            val freelancer = hashMapOf(
                "name" to name,
                "UID" to UID,
                "job" to job,
                "surname" to surname,
                "country" to country,
                "city" to city,
                "email" to email,
                "phoneNumber" to phoneNumber,
                "education" to education,
                "careerFields" to careerFields,
                "definition" to definition,
                "imageURL" to "",
                "dailyPrice" to dailyPrice,
                "rating" to 0.0f,
                "pastProjects" to mutableListOf<String>(),
                "ongoingProjects" to mutableListOf<String>(),
                "completedGivenProjects" to mutableListOf<String>(),
                "ongoingGivenProjects" to mutableListOf<String>(),
                "reviews" to mutableListOf<String>(),
                "comments" to mutableListOf<String>(),
                "subBranches" to subBranches

            )
            db.collection("Freelancers").document(UID)
                .set(freelancer)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }, { exception ->
            Log.d(TAG, "Error getting document:", exception)
        })


    }

    fun addCommentsWithUID(UID: String, comment: String) {
        db.collection("Freelancers").document(UID)
            .update("comments", mutableListOf(comment))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun getFreelancerByUID(
        UID: String,
        onSuccess: (DocumentSnapshot) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("Freelancers").document(UID).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    onSuccess(document)
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
    }
    fun getFreelancersBYSubBranches(
        subBranches: String,
        onSuccess: (List<DocumentSnapshot>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("Freelancers").whereArrayContains("subBranches", subBranches).get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    onSuccess(documents.documents)
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun addUserBYUID(
        UID: String, name: String, surname: String, country: String, city: String, email: String,
        phoneNumber: String, job: String,onSuccess: () -> Unit,
    ) {
        val user = hashMapOf(
            "name" to name,
            "UID" to UID,
            "surname" to surname,
            "country" to country,
            "city" to city,
            "email" to email,
            "imageURL" to "",
            "phoneNumber" to phoneNumber,
            "job" to job,
            "completedGivenProjects" to mutableListOf<String>(),
            "ongoingGivenProjects" to mutableListOf<String>()
        )
        db.collection("Users").document(UID)
            .set(user)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun getUserByUID(
        UID: String,
        onSuccess: (DocumentSnapshot) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("Users").document(UID).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    onSuccess(document)
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun addImageToUser(UID: String, imageURL: String) {
        db.collection("Users").document(UID)
            .update("imageURL", imageURL)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addImageToFreelancer(UID: String, imageURL: String) {
        db.collection("Freelancers").document(UID)
            .update("imageURL", imageURL)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addReviewToFreelancer(UID: String, review: String) {
        db.collection("Freelancers").document(UID)
            .update("reviews", mutableListOf(review))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }



    fun fetchChatsByUID(uid: String, onSuccess: (List<Chats>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("chats")
            .whereArrayContains("chatMembers", uid)
            .addSnapshotListener { chatSnapshot, e ->
                if (e != null) {
                    onFailure(e)
                    return@addSnapshotListener
                }
                if (chatSnapshot != null && !chatSnapshot.isEmpty) {
                    val chatList = chatSnapshot.documents.mapNotNull { it.toObject<Chats>() }
                    onSuccess(chatList)
                } else {
                    onFailure(Exception("No chats found or snapshot is null"))
                }
            }
    }
    fun getUserByUIDTwo(
        UID: String,
        onSuccess: (UserClass) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        println("girdi 2")
        try {
            db.collection("Users").document(UID).get()
                .addOnSuccessListener { document ->
                    try {
                        if (document != null) {
                            val user = document.toObject<UserClass>()
                            if (user != null) {
                                onSuccess(user)
                            } else {
                                onFailure(Exception("User is null"))
                            }
                        } else {
                            onFailure(Exception("Document is null"))
                        }
                    } catch (e: Exception) {
                        onFailure(e)
                        Log.d(TAG, "Error processing document", e)
                    }
                }
                .addOnFailureListener { exception ->
                    onFailure(exception)
                    Log.d(TAG, "get failed with ", exception)
                }
        } catch (e: Exception) {
            onFailure(e)
            Log.d(TAG, "Firestore query failed", e)
        }


    }

    fun addProject(project: ProjectClass, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("projects").document(project.UID).set(project)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
    fun getProjectsByNecessaryBranches(
        necessaryBranches: String,
        onSuccess: (List<ProjectClass>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("projects").whereArrayContains("necessaryBranches", necessaryBranches).get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    onSuccess(documents.documents.mapNotNull { it.toObject<ProjectClass>() })
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun getProjectsFreelancersID(
        UID: String,
        onSuccess: (List<ProjectClass>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("projects").whereArrayContains("freelancersID", UID).get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    onSuccess(documents.documents.mapNotNull { it.toObject<ProjectClass>() })
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }

    }
    fun getProjectsByClientID(
        UID: String,
        onSuccess: (List<ProjectClass>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("projects").whereEqualTo("clientID", UID).get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    onSuccess(documents.documents.mapNotNull { it.toObject<ProjectClass>() })
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
    }
    fun getProjectByUID(
        UID: String,
        onSuccess: (ProjectClass) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("projects").document(UID).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val project = document.toObject<ProjectClass>()
                    if (project != null) {
                        onSuccess(project)
                    } else {
                        onFailure(Exception("Project is null"))
                    }
                } else {
                    onFailure(Exception("Document is null"))
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
    }
    fun updateProjectStatusToFinished(projectUID: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val db = Firebase.firestore
        db.collection("projects").document(projectUID).update("finished", true)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

}