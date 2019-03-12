package com.merseyside.pagingtestapp.presentation.view.activity.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.BR
import com.merseyside.pagingtestapp.presentation.base.BasePropertyActivity
import com.merseyside.pagingtestapp.presentation.view.activity.model.MainViewModel
import com.merseyside.pagingtestapp.databinding.ActivityMainBinding
import com.merseyside.pagingtestapp.presentation.di.component.DaggerMainActivityComponent
import com.merseyside.pagingtestapp.presentation.di.module.MainActivityModule

class MainActivity : BasePropertyActivity<ActivityMainBinding, MainViewModel>() {

    private val propertyObserver = Observer<List<*>> {

    }

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(getMainActivityModule())
                .build().inject(this)
    }

    private fun getMainActivityModule() : MainActivityModule {
        return MainActivityModule(this)
    }

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        doLayout()
        init()
    }

    private fun doLayout() {

    }

    private fun init() {
        val items = viewModel.restoreItems()

        if (items.isNullOrEmpty()) {
            viewModel.propertiesLiveData.observe(this, propertyObserver)
            viewModel.loadProperties()
        } else {
            showPropertyList(items)
        }
    }

    private fun showPropertyList(propertyList : List<*>) {

    }
}
