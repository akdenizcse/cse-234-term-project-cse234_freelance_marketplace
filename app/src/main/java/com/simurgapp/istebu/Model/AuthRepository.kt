package com.simurgapp.istebu.Model
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    suspend fun signUp(email: String, password: String): Result<FirebaseAuthUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(FirebaseAuthUser(result.user?.uid))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signIn(email: String, password: String): Result<FirebaseAuthUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(FirebaseAuthUser(result.user?.uid))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

data class FirebaseAuthUser(val uid: String?)