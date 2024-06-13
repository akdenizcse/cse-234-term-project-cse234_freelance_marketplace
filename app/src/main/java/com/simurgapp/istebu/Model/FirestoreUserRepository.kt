package com.simurgapp.istebu.Model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

class FirestoreUserRepository {

    val db = Firebase.firestore



    fun addFreelancer(
        UID: String,
        education: String,
        careerFields: MutableList<String>,
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
                "comments" to mutableListOf<String>()

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

    fun addReviewToUser(UID: String, review: String) {
        db.collection("Users").document(UID)
            .update("reviews", mutableListOf(review))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addOngoingProjectToFreelancer(UID: String, projectUID: String) {
        db.collection("Freelancers").document(UID)
            .update("ongoingProjects", mutableListOf(projectUID))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addOngoingProjectToUser(UID: String, projectUID: String) {
        db.collection("Users").document(UID)
            .update("ongoingGivenProjects", mutableListOf(projectUID))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addCompletedProjectToFreelancer(UID: String, projectUID: String) {
        db.collection("Freelancers").document(UID)
            .update("completedGivenProjects", mutableListOf(projectUID))
            .addOnFailureListener({ e -> Log.w(TAG, "Error writing document", e) })
    }
}