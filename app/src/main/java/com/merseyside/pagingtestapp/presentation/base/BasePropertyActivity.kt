package com.merseyside.pagingtestapp.presentation.base

import androidx.databinding.ViewDataBinding
import com.merseyside.pagingtestapp.PropertyApplication
import com.merseyside.pagingtestapp.presentation.di.component.AppComponent
import com.upstream.basemvvmimpl.presentation.activity.BaseMvvmActivity

abstract class BasePropertyActivity<B : ViewDataBinding, M : BasePropertyViewModel> : BaseMvvmActivity<B, M>() {

    protected fun getAppComponent(): AppComponent = PropertyApplication.getInstance().appComponent

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}