package com.merseyside.pagingtestapp.presentation.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.merseyside.pagingtestapp.PropertyApplication
import com.merseyside.pagingtestapp.presentation.di.component.AppComponent
import com.upstream.basemvvmimpl.presentation.fragment.BaseMvvmFragment

abstract class BasePropertyFragment<B : ViewDataBinding, M : BasePropertyViewModel> : BaseMvvmFragment<B, M>() {

    protected fun getAppComponent(): AppComponent = PropertyApplication.getInstance().appComponent

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitle(context: Context): String? {
        return ""
    }

    override fun getApplicationContext(): Context {
        return baseActivityView
    }
}