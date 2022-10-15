package com.example.asteroidradar.db

import android.content.Context
import androidx.room.*
import com.example.asteroidradar.model.Asteroid

@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
abstract class AsteroidsDatabase:RoomDatabase() {
    abstract val asteroidsDao: AsteroidsDao
}
    private lateinit var INSTANCE:AsteroidsDatabase
     fun getDatabase(context: Context):AsteroidsDatabase {
        synchronized(AsteroidsDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AsteroidsDatabase::class.java,
                    "asteroids").build()
            }
            return INSTANCE
        }
    }
