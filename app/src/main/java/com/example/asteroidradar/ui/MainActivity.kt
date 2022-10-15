package com.example.asteroidradar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.work.*
import com.example.asteroidradar.R
import com.example.asteroidradar.Worker
import com.example.asteroidradar.data.Repository
import com.example.asteroidradar.db.getDatabase
import kotlinx.coroutines.*
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController

    }

}