package com.simurgapp.istebu.Model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirestoreUserRepository {
    val db = Firebase.firestore
    val city = hashMapOf(
        "name" to "Los Angeles",
        "state" to "CA",
        "country" to "USA",
    )

    fun add(){
        db.collection("cities").document("LA")
            .set(city)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }



}