package com.example.pestmanagementapp.utils

import android.content.Context
import android.content.Intent

// Function to open the document picker for images
fun openDocumentPicker(context: Context) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
    intent.type = "image/*" // Allow only images to be selected
    intent.addCategory(Intent.CATEGORY_OPENABLE) // Ensure the selected items can be opened

    // Launch the intent to let the user pick documents
    context.startActivity(intent)
}