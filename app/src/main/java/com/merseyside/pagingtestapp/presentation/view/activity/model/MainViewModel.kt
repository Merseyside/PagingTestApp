package com.merseyside.pagingtestapp.presentation.view.activity.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.merseyside.pagingtestapp.domain.Property
import com.merseyside.pagingtestapp.domain.interactor.GetPropertiesInteractor
import com.merseyside.pagingtestapp.presentation.base.BasePropertyViewModel
import com.upstream.basemvvmimpl.domain.interactor.DefaultSingleObserver

class MainViewModel(private val getPropertiesUseCase: GetPropertiesInteractor) : BasePropertyViewModel() {

    private val TAG = javaClass.simpleName

    private val propertyList = ArrayList<Property>()

    val propertiesLiveData = MutableLiveData<List<Property>>()

    override fun clearDisposables() {
        getPropertiesUseCase.clear()
    }

    override fun dispose() {
        getPropertiesUseCase.dispose()
    }

    fun restoreItems() : List<Property> {
        propertiesLiveData.value = null
        return propertyList
    }

    fun loadProperties() {
        getPropertiesUseCase.execute(PropertyObserver(), GetPropertiesInteractor.Params(5, 0))
    }

    private inner class PropertyObserver : DefaultSingleObserver<List<Property>>() {

        override fun onSuccess(obj: List<Property>) {
            super.onSuccess(obj)

            propertyList.addAll(obj)
            propertiesLiveData.value = obj

            Log.d(TAG, "$obj")
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)

            Log.d(TAG, throwable.message)
        }
    }

}