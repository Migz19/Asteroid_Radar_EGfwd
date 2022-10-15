package com.example.asteroidradar.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asteroidradar.Constants
import com.example.asteroidradar.Day
import com.example.asteroidradar.databinding.HomeBinding
import com.example.asteroidradar.db.getDatabase
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.model.PictureOfDay
import com.example.asteroidradar.retrofit.ApiCalls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AstreoidViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var asteroidList: LiveData<List<Asteroid>>
    private val _navigateToAsteroid = MutableLiveData<Asteroid?>()
    val navigateToAsteroid
        get() = _navigateToAsteroid

        lateinit var pictureOfDay : LiveData<PictureOfDay>

    private val _database = getDatabase(application)
    private val _repository = Repository(_database)

    fun getTodayDate(): String {
        return _repository.getToday()
    }

    init {
        viewModelScope.launch {
            getAsteroidsFromDB()
             getTodayPicture()
        }
    }


    private suspend fun getAsteroidsFromDB() =
        withContext(viewModelScope.coroutineContext) {
            asteroidList = liveData {
                val list = _repository.getAsteroids()
                emit(list)
            }
        }

      private suspend fun getTodayPicture() =
          withContext(viewModelScope.coroutineContext) {
              pictureOfDay = liveData {
                  val picture = _repository.getPictureOfDay()
                  emit(picture)
              }
          }


          fun onAsteroidClicked(asteroid: Asteroid) {
              _navigateToAsteroid.value = asteroid
          }

    fun onAsteroidNavigated (){
        _navigateToAsteroid.value= null
    }
}

