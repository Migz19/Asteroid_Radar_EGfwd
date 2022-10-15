package com.example.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asteroidradar.model.Asteroid

@Dao
    interface AsteroidsDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
         fun insert(list:List<Asteroid>)
        @Query("select * from `Asteroids table` order by close_approach_date asc")
         fun getAsteroids(): List<Asteroid>
        @Query("delete  from `Asteroids table`")
         fun deleteDB()
        //TODO ems7 el delete

}