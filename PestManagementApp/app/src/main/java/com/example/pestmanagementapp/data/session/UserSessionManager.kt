package com.example.pestmanagementapp.data.session

import android.content.Context
import android.content.SharedPreferences
import com.example.pestmanagementapp.data.models.UserInfo
import com.example.pestmanagementapp.utils.KeystoreManager
import javax.inject.Inject

class UserSessionManager @Inject constructor(
    private val keyManager: KeystoreManager,
    private val context: Context
) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_session",
        Context.MODE_PRIVATE)

    init {

        if (!keyManager.isKeyPresent()) {
            keyManager.generateKey()
        }
    }

    fun saveSession(userInfo: UserInfo) {
        val editor = sharedPreferences.edit()
        editor.putString("userId", keyManager.encrypt(userInfo.id))
        editor.putString("userName", keyManager.encrypt(userInfo.name))
        editor.putString("email", keyManager.encrypt(userInfo.email))
        editor.putString("passwordHash", keyManager.encrypt(userInfo.passwordHash))
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    fun getSession(): UserInfo? {
        val encryptedId = sharedPreferences.getString("userId", null)
        val encryptedName = sharedPreferences.getString("userName", null)
        val encryptedEmail = sharedPreferences.getString("email", null)
        val encryptedPasswordHash = sharedPreferences.getString("passwordHash", null)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn &&
            encryptedId != null &&
            encryptedName != null &&
            encryptedEmail != null &&
            encryptedPasswordHash != null) {

            val id = keyManager.decrypt(encryptedId)
            val name = keyManager.decrypt(encryptedName)
            val email = keyManager.decrypt(encryptedEmail)
            val passwordHash = keyManager.decrypt(encryptedPasswordHash)

            return UserInfo(id = id, name = name, email = email, passwordHash = passwordHash)
        }

        return null
    }

    fun clearSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getDarkModePreference(): Boolean {
        return sharedPreferences.getBoolean("isDarkModeEnabled", false)
    }

    fun saveDarkModePreference(isDarkModeEnabled: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkModeEnabled", isDarkModeEnabled)
        editor.apply()
    }

}