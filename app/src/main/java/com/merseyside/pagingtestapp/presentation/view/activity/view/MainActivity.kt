package com.merseyside.pagingtestapp.presentation.view.activity.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.merseyside.pagingtestapp.R
import com.merseyside.pagingtestapp.BR
import com.merseyside.pagingtestapp.presentation.base.BasePropertyActivity
import com.merseyside.pagingtestapp.presentation.view.activity.model.MainViewModel
import com.merseyside.pagingtestapp.databinding.ActivityMainBinding
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.domain.repository.datasource.State
import com.merseyside.pagingtestapp.presentation.di.component.DaggerMainActivityComponent
import com.merseyside.pagingtestapp.presentation.di.module.MainActivityModule
import com.merseyside.pagingtestapp.presentation.view.activity.adapter.PropertyAdapter
import com.merseyside.pagingtestapp.utils.WrapContentLinearLayoutManager

class MainActivity : BasePropertyActivity<ActivityMainBinding, MainViewModel>() {

    private val TAG = javaClass.simpleName

    private val INITIAL_KEY = "initial"

    private lateinit var adapter : PropertyAdapter

    private val propertyObserver = Observer<PagedList<PropertyModel>> {
        Log.d(TAG, "${it?.size ?: 0}")
        adapter.submitList(it)
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
        init(savedInstance?.getInt(INITIAL_KEY) ?: 0)
    }

    private fun doLayout() {

        viewDataBinding.propertyList.layoutManager = WrapContentLinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = PropertyAdapter()
        viewDataBinding.propertyList.adapter = adapter
    }

    private fun init(initialKey : Int) {
        viewModel.loadProperties(initialKey)
        viewModel.propertyLiveData.observe(this, propertyObserver)

        viewModel.getStateLiveData().observe(this, Observer<State> {
            when(it) {
                State.ERROR ->
                    showErrorMsg("Error occured")
            }
        })

    }

    private fun showPropertyList(propertyList : List<*>) {

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putInt(INITIAL_KEY, (viewDataBinding.propertyList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
    }
}
