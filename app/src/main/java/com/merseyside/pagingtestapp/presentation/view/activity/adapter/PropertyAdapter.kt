package com.merseyside.pagingtestapp.presentation.view.activity.adapter

import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.upstream.basemvvmimpl.BR
import com.upstream.basemvvmimpl.presentation.adapter.BasePagedAdapter

class PropertyAdapter : BasePagedAdapter<PropertyModel>(PropertyModel.DIFF_CALLBACK) {

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.view_property_item
    }

    override fun getBindingVariable(): Int {
        return BR.obj
    }

}