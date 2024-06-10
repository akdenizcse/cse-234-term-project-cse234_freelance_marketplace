package com.simurgapp.istebu.Model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

class FirestoreUserRepository {

    val db = Firebase.firestore
    val UID = "123456789"


    fun addFreelancer(education: String , careerFields: MutableList<String>, definition: String , imageURL: String  = "", dailyPrice: Int ){

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
                "imageURL" to imageURL,
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
            Log.w(TAG, "Error getting user document", exception)
        })

    }
    fun addCommentsWithUID(UID: String, comment: String){
        db.collection("Freelancers").document(UID)
            .update("comments", mutableListOf(comment))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
    fun getFreelancerByUID(UID: String, onSuccess: (DocumentSnapshot) -> Unit, onFailure: (Exception) -> Unit) {
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
    fun addUserBYUID(UID: String, name: String , surname: String , country: String , city: String , email: String ,
                     phoneNumber: String , job: String){
        val user = hashMapOf(
            "name" to name,
            "UID" to UID,
            "surname" to surname,
            "country" to country,
            "city" to city,
            "email" to email,
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
    fun getUserByUID(UID: String, onSuccess: (DocumentSnapshot) -> Unit, onFailure: (Exception) -> Unit) {
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
}
