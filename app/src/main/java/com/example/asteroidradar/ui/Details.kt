package com.example.asteroidradar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.DetailsFragmentBinding

class Details : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val asteroid = DetailsArgs.fromBundle(requireArguments()).selectedAsteroid
        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }
        binding.asteroid=asteroid
        return binding.root
    }
    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }

}