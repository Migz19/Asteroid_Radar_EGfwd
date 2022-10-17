package com.example.asteroidradar.db

import android.content.Context
import androidx.room.*
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.model.PictureOfDay
import com.example.asteroidradar.retrofit.PicApi

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
@Database (entities = [PictureOfDay::class], version = 1, exportSchema = false)
abstract class PictureDB:RoomDatabase(){
    abstract val picDao:PicDao
}
private lateinit var PIC_INSTANCE:PictureDB
fun getPicDatabase(context: Context):PictureDB{
    synchronized(PictureDB::class.java){
        if (!::PIC_INSTANCE.isInitialized) {
            PIC_INSTANCE = Room.databaseBuilder(context.applicationContext,
                PictureDB::class.java,
                "PICTURE").build()
        }
        return PIC_INSTANCE
    }
    }
