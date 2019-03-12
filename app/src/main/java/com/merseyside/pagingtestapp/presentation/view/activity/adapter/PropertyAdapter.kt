package com.merseyside.pagingtestapp.presentation.view.activity.adapter

import com.merseyside.pagingtestapp.domain.Property
import com.merseyside.pagingtestapp.presentation.view.activity.model.PropertyItemViewModel
import com.upstream.basemvvmimpl.presentation.adapter.BasePagedAdapter

class PropertyAdapter : BasePagedAdapter<Property, PropertyItemViewModel>() {

    override fun getLayoutIdForPosition(position: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBindingVariable(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}