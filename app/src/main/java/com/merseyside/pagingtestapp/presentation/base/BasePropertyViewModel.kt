package com.merseyside.pagingtestapp.presentation.base

import android.content.Context
import android.os.Bundle
import com.upstream.basemvvmimpl.presentation.model.BaseViewModel

abstract class BasePropertyViewModel(bundle : Bundle? = null) : BaseViewModel(bundle) {


    override fun updateLanguage(context: Context) {
    }
}