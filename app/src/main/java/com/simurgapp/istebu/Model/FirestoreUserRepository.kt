package com.simurgapp.istebu.Model

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage

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

        getUserByUID(UID, {
            val freelancer = hashMapOf(
                "name" to it.name,
                "UID" to UID,
                "job" to it.job,
                "surname" to it.surname,
                "country" to it.country,
                "city" to it.city,
                "email" to it.email,
                "phoneNumber" to it.phoneNumber,
                "education" to education,
                "careerFields" to careerFields,
                "definition" to definition,
                "imageURL" to it.imageURL,
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
        onSuccess: (UserClass) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("Users").document(UID).get()
            .addOnSuccessListener { document ->
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
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.d(TAG, "get failed with ", exception)
            }
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
    fun addOfferToProject(projectUID: String, offer: OffersClass, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("projects").document(projectUID).update("offers", mutableListOf(offer))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
    fun uploadImageForUser(userId: String, imageUri: Uri , onSuccess: (Uri) -> Unit, onFailure: (Exception) -> Unit){
        val storageRef = Firebase.storage.reference
        val userRef = storageRef.child("users/$userId/${imageUri.lastPathSegment}")

        val uploadTask = userRef.putFile(imageUri)

        uploadTask.addOnSuccessListener {

            userRef.downloadUrl.addOnSuccessListener { uri ->
                onSuccess(uri)

            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)

        }
    }
    fun getImageForUser(userId: String, onSuccess: (Uri) -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = Firebase.storage.reference

        val userRef = storageRef.child("users/$userId")

        userRef.listAll().addOnSuccessListener { listResult ->
            if (!listResult.items.isNullOrEmpty()) {
                val tasks = listResult.items.map { itemRef ->
                    itemRef.metadata
                }

                Tasks.whenAllComplete(tasks).addOnSuccessListener {
                    val sortedItems = listResult.items.sortedByDescending { itemRef ->
                        itemRef.metadata.result.creationTimeMillis
                    }

                    if (sortedItems.isNotEmpty()) {
                        sortedItems[0].downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri
                            onSuccess(imageUrl)
                        }.addOnFailureListener { exception ->
                            onFailure(exception)
                        }
                    } else {
                        onFailure(Exception("Kullanıcının yüklenmiş resmi bulunamadı."))
                    }
                }.addOnFailureListener { exception ->
                    onFailure(exception)
                }
            } else {
                onFailure(Exception("Kullanıcının yüklenmiş resmi bulunamadı."))
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }
    fun uploadImagesForProject(projectId: String, imageUris: List<Uri>, onSuccess: (List<String>) -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = Firebase.storage.reference
        val imageUrls = mutableListOf<String>()
        val uploadTasks = mutableListOf<UploadTask>()

        // Tüm resimleri sırayla yükle
        imageUris.forEachIndexed { index, imageUri ->
            val imageName = "image_${index + 1}_${System.currentTimeMillis()}.${imageUri.pathSegments.lastOrNull()}"
            val projectRef = storageRef.child("projects/$projectId/$imageName")

            val uploadTask = projectRef.putFile(imageUri)

            uploadTask.addOnSuccessListener { taskSnapshot ->
                // Yükleme başarılı olduğunda resmin URL'sini al
                projectRef.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    imageUrls.add(imageUrl)

                    // Tüm resimler yüklendiyse onSuccess callback'i çağır
                    if (imageUrls.size == imageUris.size) {
                        onSuccess(imageUrls)
                    }
                }.addOnFailureListener { exception ->
                    onFailure(exception)
                }
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }

            // UploadTask listesine ekle
            uploadTasks.add(uploadTask)
        }
    }

    fun getImagesForProject(projectId: String, onSuccess: (List<String>) -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = Firebase.storage.reference
        val projectRef = storageRef.child("projects/$projectId")

        projectRef.listAll()
            .addOnSuccessListener { listResult ->
                val imageUrls = mutableListOf<String>()
                val downloadTasks = mutableListOf<Task<Uri>>()

                listResult.items.forEach { itemRef ->
                    val downloadTask = itemRef.downloadUrl
                    downloadTasks.add(downloadTask)

                    downloadTask.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        imageUrls.add(imageUrl)

                        if (imageUrls.size == listResult.items.size) {
                            onSuccess(imageUrls)
                        }
                    }.addOnFailureListener { exception ->
                        onFailure(exception)
                    }
                }
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun addCommitAndRatingToFreeLancer(UID: String, commit: String, rating: Float) {
        db.collection("Freelancers").document(UID)
            .update("comments", mutableListOf(commit))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        db.collection("Freelancers").document(UID)
            .update("rating", rating)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addOffer(offer: OffersClass, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("offers").document(offer.UID).set(offer)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
    fun getOffersByProjectId(
        projectID: String,
        onSuccess: (List<OffersClass>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("offers").whereEqualTo("projectID", projectID).get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    onSuccess(documents.documents.mapNotNull { it.toObject<OffersClass>() })
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