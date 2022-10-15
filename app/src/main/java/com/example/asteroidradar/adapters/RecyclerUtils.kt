package com.example.asteroidradar.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.asteroidradar.model.Asteroid

class AsteroidDiff : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return (oldItem.id==newItem.id&&oldItem.name==newItem.name)
    }
}
class AsteroidClickListener(val clickListener: (asteroid:Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)

}