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

    fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
