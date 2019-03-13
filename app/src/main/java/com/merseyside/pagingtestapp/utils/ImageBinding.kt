package com.merseyside.pagingtestapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.merseyside.pagingtestapp.R


@BindingAdapter("bind:imageUrl")
fun loadImage(iv: ImageView, url: String?) {
    Glide.with(iv)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv)
}

