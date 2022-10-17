package com.example.asteroidradar.data

import android.app.Application
import androidx.lifecycle.*

import com.example.asteroidradar.db.getDatabase
import com.example.asteroidradar.db.getPicDatabase
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.model.PictureOfDay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AstreoidViewModel(application: Application) : AndroidViewModel(application){
    lateinit var asteroidList: LiveData<List<Asteroid>>
    private val _navigateToAsteroid = MutableLiveData<Asteroid?>()
    val navigateToAsteroid
        get() = _navigateToAsteroid

    lateinit var pictureOfDay: LiveData<PictureOfDay>

    private val _database = getDatabase(application)
    private val _picDB = getPicDatabase(application)
    private val _repository = Repository(_database, _picDB)



    init {
        viewModelScope.launch {
            getAsteroidsFromDB()
            getTodayPictureFromDB()
        }
    }


    private suspend fun getAsteroidsFromDB() =
        withContext(viewModelScope.coroutineContext) {
            asteroidList = liveData {
                val list = _repository.getAsteroids()
                emit(list)
            }
        }

    private suspend fun getTodayPictureFromDB() =
        withContext(viewModelScope.coroutineContext) {
            pictureOfDay = liveData {
                val picture = _repository.getPicFromDB()
                if (picture != null) {
                    emit(picture)
                }
            }
        }


    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToAsteroid.value = null
    }

}

