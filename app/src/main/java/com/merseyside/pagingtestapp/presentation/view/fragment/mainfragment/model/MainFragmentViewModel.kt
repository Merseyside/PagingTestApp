package com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.data.repository.datasource.PropertyDataSource
import com.merseyside.pagingtestapp.data.repository.datasource.PropertyDataSourceFactory
import com.merseyside.pagingtestapp.data.repository.datasource.State
import com.merseyside.pagingtestapp.presentation.base.BasePropertyViewModel
import com.merseyside.pagingtestapp.presentation.navigation.Screens
import ru.terrakok.cicerone.Router


class MainFragmentViewModel(private val router : Router,
                            private val propertyDataSourceFactory: PropertyDataSourceFactory) : BasePropertyViewModel() {

    private val TAG = javaClass.simpleName

    lateinit var propertyLiveData: LiveData<PagedList<PropertyModel>>

    override fun clearDisposables() {}

    override fun dispose() {}

    private fun initPaging() {

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(15)
                .setPageSize(10)
                .setPrefetchDistance(5)
                .build()

        propertyLiveData = LivePagedListBuilder<Int, PropertyModel>(propertyDataSourceFactory, pagedListConfig)
                .build()
    }

    fun loadProperties() {
        initPaging()
    }

    fun getStateLiveData(): LiveData<State> = Transformations.switchMap<PropertyDataSource,
            State>(propertyDataSourceFactory.propertyDataSourceLiveData, PropertyDataSource::state)

    override fun onCleared() {
        super.onCleared()
        propertyDataSourceFactory.getCompositeDisposables().dispose()
    }

    fun navigateToDetailsFragment() {
        router.navigateTo(Screens.DetailFragmentScreen())
    }

    fun retry() {
        propertyDataSourceFactory.propertyDataSourceLiveData.value?.retry()
    }
}