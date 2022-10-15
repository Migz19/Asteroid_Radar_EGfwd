package com.example.asteroidradar.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.example.asteroidradar.Constants
import com.example.asteroidradar.Day
import com.example.asteroidradar.R
import com.example.asteroidradar.Worker
import com.example.asteroidradar.adapters.AsteroidClickListener
import com.example.asteroidradar.adapters.FeedAdapter
import com.example.asteroidradar.adapters.bindTodayImage
import com.example.asteroidradar.adapters.bindTodayImageTitle
import com.example.asteroidradar.data.AstreoidViewModel
import com.example.asteroidradar.databinding.HomeBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Home : Fragment() {
    private lateinit var model: AstreoidViewModel
    private lateinit var binding: HomeBinding
    private val adapter = FeedAdapter(AsteroidClickListener { asteroid ->
        model.onAsteroidClicked(asteroid)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Default).launch {
            doWork()
        }
        model = ViewModelProvider(this)[AstreoidViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = HomeBinding.inflate(inflater, container, false)
        binding.viewmodel = model
        bindRecycler(adapter)
        model.pictureOfDay.observe(viewLifecycleOwner){ picture->
            bindTodayImage(binding.activityMainImageOfTheDay,picture.url)
            bindTodayImageTitle(binding.nameTV,picture.title)
        }
        model.navigateToAsteroid.observe(viewLifecycleOwner) { asteroid ->
            asteroid?.let {
                findNavController().navigate(
                    HomeDirections.actionHomeToDetails(asteroid))
                model.onAsteroidNavigated()
            }
        }


        binding.executePendingBindings()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun doWork() {
        val fetchDataRequest = PeriodicWorkRequestBuilder<Worker>(
            1, TimeUnit.DAYS
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(
                        NetworkType.CONNECTED
                    )
                    .build()
            ).build()
        val workManager = WorkManager.getInstance(requireContext())
        val workInfo = workManager
            .getWorkInfosForUniqueWorkLiveData("fetch_data")
            .value
        workManager.enqueueUniquePeriodicWork("fetch_data", ExistingPeriodicWorkPolicy.KEEP, fetchDataRequest)

    }

    private fun bindRecycler(adapter: FeedAdapter) {
        model.asteroidList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
                binding.asteroidRecycler.adapter = adapter
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.today_asteroids -> {
                adapter.submitList(adapter.currentList.filter {
                    it.close_approach_date == model.getTodayDate()
                })
            }
            R.id.week_asteroids -> {
                adapter.submitList(model.asteroidList.value)
            }
            R.id.saved_Asteroids -> {
                adapter.submitList(model.asteroidList.value)
            }
            else -> return true
        }
        return true
    }


}