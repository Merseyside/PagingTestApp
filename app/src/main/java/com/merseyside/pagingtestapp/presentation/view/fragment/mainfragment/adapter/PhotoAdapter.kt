package com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.merseyside.pagingtestapp.domain.Photos
import com.merseyside.pagingtestapp.presentation.view.view.ImageLoaderView

class PhotoAdapter(private val context : Context,
                   private val photoUrls : Array<Photos>?) : PagerAdapter() {


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return if (photoUrls.isNullOrEmpty())
            1
        else {
            photoUrls.size
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var photos : Photos? = null

        if (!photoUrls.isNullOrEmpty()) {
            photos = photoUrls[position]
        }

        val imageLoader = ImageLoaderView(context, photos)
        imageLoader.isClickable = false

        container.addView(imageLoader)

        return imageLoader
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
}