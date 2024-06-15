package com.example.loginproject.session
import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "SessionPrefs"
    private const val KEY_USERNAME = "username"    // name
    private const val KEY_IMSI = "imsi"            // IMSI
    private const val KEY_MOBILE = "mobile"        // mobile no
    private const val KEY_CITY = "city"            // city

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveCredentials(context: Context, username : String, imsi: String, mobileNo: String, city : String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_USERNAME,username)
        editor.putString(KEY_IMSI, imsi)
        editor.putString(KEY_MOBILE, mobileNo)
        editor.putString(KEY_CITY, city)
        editor.apply()
    }

    fun getSavedUsername(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_USERNAME, null)
    }

    fun getSavedMobileNo(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_MOBILE, null)
    }

    fun getSavedCity(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_CITY, null)
    }

    fun getSavedIMSI(context: Context): String? {
        return getSharedPreferences(context).getString(KEY_IMSI, null)
    }

    fun clearSession(context: Context) {
//        val editor = getSharedPreferences(context).edit()
//        editor.clear()
//        editor.apply()
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Clear all session data
        editor.apply()
    }
}
