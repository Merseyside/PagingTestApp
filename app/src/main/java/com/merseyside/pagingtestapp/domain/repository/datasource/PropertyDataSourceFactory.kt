package com.merseyside.pagingtestapp.domain.repository.datasource

import androidx.paging.DataSource
import com.merseyside.pagingtestapp.domain.PropertyModel
import androidx.lifecycle.MutableLiveData
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class PropertyDataSourceFactory @Inject constructor(
        private val dataRepository: DataRepository
): DataSource.Factory<Int, PropertyModel>() {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    val propertyDataSourceLiveData = MutableLiveData<PropertyDataSource>()

    override fun create(): DataSource<Int, PropertyModel> {
        val propertyDataSource = PropertyDataSource(dataRepository, compositeDisposable)
        propertyDataSourceLiveData.postValue(propertyDataSource)
        return propertyDataSource
    }

    fun getCompositeDisposables() = compositeDisposable
}