package com.example.asteroidradar.work
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradar.data.Repository
import com.example.asteroidradar.db.getDatabase
import com.example.asteroidradar.model.PictureOfDay

class DeleteWorker(private val appContext: Context, private val params: WorkerParameters) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORKER_NAME = "DeleteDataWorker"
    }

    override suspend fun doWork(): Result {
        val repo = Repository(getDatabase(applicationContext))
        val today = params.inputData.getString("today")
        repo.deleteOldFromDB(today!!)
        val pic = PictureOfDay("picture","title","url")
        repo.savePicOfDay(pic)
        return Result.success()
    }
}

