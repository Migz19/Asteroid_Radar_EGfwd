package com.example.asteroidradar.db

import androidx.room.*
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.model.PictureOfDay

@Dao
    interface AsteroidsDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
         fun insert(list:List<Asteroid>)
        @Query("select * from `Asteroids table` order by close_approach_date asc")
         fun getAsteroids(): List<Asteroid>
        @Query("delete  from `Asteroids table`")
         fun deleteDB()
        //TODO ems7 el delete
        @Query("delete from `Asteroids table` where close_approach_date<Date(:date)")
        fun deleteAsteroidsBeforeToday(date: String?)

}
@Dao
interface PicDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictureOfDay(picture:PictureOfDay)
    @Query("select * from `Pictures Database`")
    fun getPictureOFDay():List<PictureOfDay>
}