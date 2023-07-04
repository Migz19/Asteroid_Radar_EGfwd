package com.example.asteroidradar.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.asteroidradar.Constants
import com.example.asteroidradar.Day
import com.example.asteroidradar.data.Repository
import com.example.asteroidradar.db.getDatabase
import com.example.asteroidradar.db.getPicDatabase

class RefreshWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORKER_NAME = "RefreshDataWorker"
    }

    private val repo = Repository(getDatabase(appContext), getPicDatabase(appContext))
    override suspend fun doWork(): Result {
        Log.d("worker", "worker works")
        val today = Day.getToday()
        try {
            val asteroidsList = repo.refreshData(today)
            workDataOf(today to "today")
                repo.saveData(asteroidsList)
            val pictureOfDay = repo.getPictureOfDay()
            repo.savePicOfDay(pictureOfDay)
            Log.d("worker", "succeeded")
            return Result.success()
        } catch (e: Exception) {
            Log.d("worker", "failed")

            workDataOf(Constants.ERROR_MSG to e.localizedMessage)
            return Result.retry()
        }
    }


}