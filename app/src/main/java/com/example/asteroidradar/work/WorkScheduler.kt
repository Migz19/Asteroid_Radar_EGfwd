package com.example.asteroidradar.work

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class WorkScheduler : Application() {
   private val _applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
      _applicationScope.launch {
          doWork()
          Log.d("in work","in work")
      }}
    private fun doWork(){
        Log.d("workmanager","workmanager works")
        val workManager = WorkManager.getInstance(applicationContext)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
           // .setRequiresCharging(true)
           .build()
        val fetchDataRequest = PeriodicWorkRequestBuilder<RefreshWorker>(1, TimeUnit.DAYS).setConstraints(constraints).build()
        val deleteDataRequest = PeriodicWorkRequestBuilder<DeleteWorker>(1,TimeUnit.DAYS).setConstraints(constraints).build()
        workManager.enqueueUniquePeriodicWork(RefreshWorker.WORKER_NAME, ExistingPeriodicWorkPolicy.KEEP, fetchDataRequest)
        workManager.enqueueUniquePeriodicWork(DeleteWorker.WORKER_NAME, ExistingPeriodicWorkPolicy.KEEP, deleteDataRequest)

    }

}