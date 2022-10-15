package com.example.asteroidradar.adapters
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.databinding.AsteroidItemBinding
import com.example.asteroidradar.model.Asteroid

class FeedAdapter(val clickListener: AsteroidClickListener): ListAdapter<Asteroid,FeedAdapter.FeedVH>(AsteroidDiff()) {

    class FeedVH( val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Asteroid,clickListener: AsteroidClickListener) {
            binding.asteroid = item
            binding.clickListener=clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun inflateItem(parent: ViewGroup): FeedVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, parent, false)
                return FeedVH(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedVH {
        return FeedVH.inflateItem(parent)
    }

    override fun onBindViewHolder(holder: FeedVH, position: Int) {
        val item = getItem(position)
          holder.bind(item,clickListener)

    }

}

