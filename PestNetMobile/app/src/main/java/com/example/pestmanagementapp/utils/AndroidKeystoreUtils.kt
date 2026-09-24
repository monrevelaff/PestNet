package com.example.pestmanagementapp.utils

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject


/**
 * Portions of this code are adapted from:
 * Mouad Oumous, “How to Use the Android Keystore for Secure Data Storage,”
 * Medium, Aug 6, 2023. Available at:
 * https://medium.com/javarevisited/how-to-use-the-android-keystore-for-secure-data-storage-2cbb3ccf2eee
 *
 * Additional references:
 * Google Android Developers, “Android Keystore System,” 2024
 * https://developer.android.com/training/articles/keystore
 *
 */

class KeystoreManager @Inject constructor(private val context: Context) {


    private val keystore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val keyAlias = "secure_alias_key" // Unique alias


    fun generateKey() {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        keyGenerator.init(
            KeyGenParameterSpec.Builder(keyAlias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
        )
        keyGenerator.generateKey()
    }

    fun isKeyPresent(): Boolean {
        return try {
            keystore.getKey(keyAlias, null) != null
        } catch (e: Exception) {
            false
        }
    }

    fun getKey(): SecretKey {
        return keystore.getKey(keyAlias, null) as SecretKey
    }


    fun encrypt(plainText: String): String {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, getKey())
        val iv = cipher.iv

        val encryption = cipher.doFinal(plainText.toByteArray())
        val combined = iv + encryption

        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    fun decrypt(encryptedText: String): String {
        val decodedData = Base64.decode(encryptedText, Base64.DEFAULT)

        val ivSize = 12
        if (decodedData.size <= ivSize) {
            throw IllegalArgumentException("Invalid encrypted data; too short to contain IV.")
        }

        val iv = decodedData.copyOfRange(0, ivSize)
        val encrypted = decodedData.copyOfRange(ivSize, decodedData.size)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, getKey(), spec)

        val decryptedData = cipher.doFinal(encrypted)
        return String(decryptedData)
    }
}