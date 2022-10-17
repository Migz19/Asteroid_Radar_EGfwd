package com.example.asteroidradar.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "Asteroids table")
@Parcelize
data class Asteroid (
    @PrimaryKey
    val id: Long,
    val name: String,
    val close_approach_date: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    var isHazardous:Boolean,
    ):Parcelable

