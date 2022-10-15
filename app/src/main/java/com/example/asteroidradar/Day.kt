package com.example.asteroidradar

import android.annotation.SuppressLint
import java.util.*

interface Day {
     fun getToday(): String
    @SuppressLint("WeekBasedYear")
     fun formatDate(date: Date): String
}