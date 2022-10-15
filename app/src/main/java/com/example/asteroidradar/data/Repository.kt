package com.example.asteroidradar.data

import android.util.Log
import com.example.asteroidradar.Constants
import com.example.asteroidradar.Day
import com.example.asteroidradar.db.AsteroidsDatabase
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.model.PictureOfDay
import com.example.asteroidradar.retrofit.Api
import com.example.asteroidradar.retrofit.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Repository(private val database: AsteroidsDatabase):Day {
    private val _apiCalls = Api.apiCalls
    private lateinit var _asteroidsList: List<Asteroid>

    suspend fun refreshData(startDate: String) {
        val response = _apiCalls.getAsteroids(startDate)
        _asteroidsList = parseAsteroidsJsonResult(JSONObject(response))
        try {
            withContext(Dispatchers.IO) {
                saveData(_asteroidsList)
            }
        } catch (e: Exception) {
            Log.d("exception in repo", e.message.toString())
        }
    }

    private fun saveData(list: List<Asteroid>) {
        database.asteroidsDao.insert(list)
    }

    suspend fun getAsteroids(): List<Asteroid> {
        return  withContext (Dispatchers.IO) {
             database.asteroidsDao.getAsteroids()
        }
    }

    suspend fun getPictureOfDay() :PictureOfDay{
        return Api.apiCalls.getPictureOfDay()
    }
    override fun getToday(): String {
        val calendar = Calendar.getInstance()
        return formatDate(calendar.time)
    }

    override fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(date)
    }
}

