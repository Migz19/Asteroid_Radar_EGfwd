package com.example.asteroidradar

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Day {
    fun getToday(): String{
        val calendar = Calendar.getInstance()
        return formatDate(calendar.time)
    }
    @SuppressLint("WeekBasedYear")
    fun formatDate(date: Date): String{
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(date)
    }
}