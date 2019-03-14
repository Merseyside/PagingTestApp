package com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.adapter

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.upstream.basemvvmimpl.BR
import com.upstream.basemvvmimpl.presentation.adapter.BasePagedAdapter
import com.upstream.basemvvmimpl.presentation.view.BaseViewHolder


class PropertyAdapter : BasePagedAdapter<PropertyModel>(PropertyModel.DIFF_CALLBACK) {

    override fun getLayoutIdForPosition(position: Int): Int {
        return com.merseyside.pagingtestapp.R.layout.view_property_item
    }

    override fun getBindingVariable(): Int {
        return BR.obj
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val photoAdapter = PhotoAdapter(holder.itemView.context, getItem(position)?.photoUrls?.toTypedArray())
        val gallery = holder.itemView.findViewById<ViewPager>(com.merseyside.pagingtestapp.R.id.gallery)

        gallery.adapter = photoAdapter

        gallery.setOnTouchListener(object : View.OnTouchListener {
            var oldX = 0f
            var newX = 0f
            var sens = 5f

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> oldX = event.x

                    MotionEvent.ACTION_UP -> {
                        newX = event.x
                        if (Math.abs(oldX - newX) < sens) {
                            getOnItemClickListener()?.onItemClicked(getItem(position)!!)
                            return true
                        }
                        oldX = 0f
                        newX = 0f
                    }
                }

                return false
            }
        })

    }

}