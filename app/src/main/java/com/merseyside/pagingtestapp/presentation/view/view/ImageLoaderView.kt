package com.merseyside.pagingtestapp.presentation.view.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.domain.Photos


@SuppressLint("ViewConstructor")
class ImageLoaderView(context : Context, photoUrl : Photos?) : ImageView(context) {

    init {
        scaleType = ImageView.ScaleType.FIT_CENTER
        adjustViewBounds = true

        Glide.with(context)
                .load(photoUrl?.sizeMediumUrl)
                .placeholder(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
    }
}