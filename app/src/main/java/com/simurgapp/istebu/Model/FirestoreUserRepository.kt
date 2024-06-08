package com.simurgapp.istebu.Model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirestoreUserRepository {

    val db = Firebase.firestore
    val UID = "123456789"


    fun add(name: String , surname: String , country: String , city: String , email: String ,
            phoneNumber: String , education: String , careerFields: MutableList<String>, definition: String , imageURL: String  = "", dailyPrice: Int ){
        val freelancer = hashMapOf(
            "name" to name,


            "UID" to UID,
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
            "email" to email,
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
    }
    fun addCommentsWithUID(UID: String, comment: String){
        db.collection("Freelancers").document(UID)
            .update("comments", mutableListOf(comment))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }



}