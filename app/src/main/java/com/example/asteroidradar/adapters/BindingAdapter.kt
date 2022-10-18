package com.example.asteroidradar.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.asteroidradar.R
import com.example.asteroidradar.model.PictureOfDay
import com.squareup.picasso.Picasso

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    val numberString = String.format(context.getString(R.string.astronomical_unit_format), number)
    textView.text = numberString
    textView.contentDescription = numberString
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    val numberString = String.format(context.getString(R.string.km_unit_format), number)
    textView.text = numberString
    textView.contentDescription = numberString
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    val numberString = String.format(context.getString(R.string.km_s_unit_format), number)
    textView.text = numberString
    textView.contentDescription = numberString
}

@BindingAdapter("todayImage")
fun bindTodayImage(imageView: ImageView, url: String) {
    val context = imageView.context
    Picasso.get().load(url).into(imageView)
    imageView.contentDescription = context.getString(R.string.image_of_the_day)
}

@BindingAdapter("todayImageTitle")
fun bindTodayImageTitle(textView: TextView, title: String) {
    textView.text = title
    textView.contentDescription = title
}

@BindingAdapter("tvContentDescription")
fun tvContentDescription(textView: TextView, description: String) {
    textView.contentDescription = description
}

@BindingAdapter("isHazardousCD")
fun isHazardousCD(imageView: ImageView, isHazardous: Boolean) {
    val context = imageView.context
    when (isHazardous) {
        true -> imageView.contentDescription = context.getString(R.string.potentially_hazardous_asteroid_image)
        false -> imageView.contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
}


