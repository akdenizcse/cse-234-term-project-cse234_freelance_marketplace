package com.simurgapp.istebu.Model
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    fun saveUID(uid: String) {
        sharedPreferences.edit().putString("uid", uid).apply()
    }
    fun getUID(): String? {
        return sharedPreferences.getString("uid", null)
    }
    fun saveEmail(email: String) {
        sharedPreferences.edit().putString("email", email).apply()
    }
    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("is_logged_in", false)
    }
    fun isUserCreated(): Boolean {
        return sharedPreferences.getBoolean("is_user_created", false)
    }
    fun saveUserCreated(isUserCreated: Boolean) {
        sharedPreferences.edit().putBoolean("is_user_created", isUserCreated).apply()
    }
    fun isFreelancerCreated(): Boolean {
        return sharedPreferences.getBoolean("is_freelancer_created", false)
    }
    fun saveFreelancerCreated(isFreelancerCreated: Boolean) {
        sharedPreferences.edit().putBoolean("is_freelancer_created", isFreelancerCreated).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}