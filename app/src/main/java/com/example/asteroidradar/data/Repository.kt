package com.example.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asteroidradar.Constants
import com.example.asteroidradar.db.AsteroidsDatabase
import com.example.asteroidradar.db.PictureDB
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.model.PictureOfDay
import com.example.asteroidradar.retrofit.Api
import com.example.asteroidradar.retrofit.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Repository(private var database: AsteroidsDatabase) {
    private lateinit var pictureDB: PictureDB

    constructor(asteroidsDatabase: AsteroidsDatabase, pictureDB: PictureDB) : this(asteroidsDatabase) {
        this.pictureDB = pictureDB
    }

    private val _apiCalls = Api.apiCalls

    suspend fun refreshData(startDate: String): List<Asteroid> {
        return withContext(Dispatchers.IO) {
            val response = _apiCalls.getAsteroids(startDate)
            parseAsteroidsJsonResult(JSONObject(response))
        }
    }

    fun saveData(list: List<Asteroid>) {
        database.asteroidsDao.insert(list)
    }

    suspend fun getAsteroids(): List<Asteroid> {
        return withContext(Dispatchers.IO) {
            database.asteroidsDao.getAsteroids()
        }
    }

    suspend fun getPictureOfDay(): PictureOfDay {
        return Api.picApi.getPictureOfDay()
    }

    fun savePicOfDay(picture: PictureOfDay) {
        pictureDB.picDao.insertPictureOfDay(picture)
    }

    fun deleteOldFromDB(date: String) {
        database.asteroidsDao.deleteAsteroidsBeforeToday(date)
    }

    suspend fun getPicFromDB(): PictureOfDay? {
        val picture: PictureOfDay
        return withContext(Dispatchers.IO) {
            val picList = pictureDB.picDao.getPictureOFDay()
            if (picList.isNotEmpty()) {
                picture = picList[picList.size - 1]
                picture
            } else
                null
        }
    }


}

