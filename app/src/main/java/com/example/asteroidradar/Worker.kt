package com.example.asteroidradar

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.asteroidradar.data.Repository
import com.example.asteroidradar.db.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Worker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params),Day {
    private val repo = Repository(getDatabase(appContext))
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                repo.refreshData(getToday())
                Log.d("worker", "succeeded")
                return@withContext Result.success()

            } catch (e: IOException) {
                Log.d("worker", "failed")
                return@withContext Result.failure(
                    workDataOf(Constants.ERROR_MSG to e.localizedMessage)
                )
            }
        }
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