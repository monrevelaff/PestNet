package com.example.pestmanagementapp.utils

import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

// Function to format the date from "yyyy-MM-dd" to "MMMM dd, yyyy"
fun formatTimeStamp(timeStamp : Long ): String {
    val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    return sdf.format(Date(timeStamp))
}