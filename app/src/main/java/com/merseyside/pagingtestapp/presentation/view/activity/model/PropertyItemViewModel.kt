package com.merseyside.pagingtestapp.presentation.view.activity.model

import com.merseyside.pagingtestapp.domain.Property
import com.upstream.basemvvmimpl.presentation.model.BaseComparableAdapterViewModel

class PropertyItemViewModel(private val item : Property) : BaseComparableAdapterViewModel<Property>() {

    override fun compareTo(obj: Property): Int {
        return item.compareTo(obj)
    }

    override fun isContentTheSame(obj: Property): Boolean {
        return false
    }

    override fun isItemsTheSame(obj: Property): Boolean {
        return false
    }

    override fun getItem(): Property {
        return item
    }
}