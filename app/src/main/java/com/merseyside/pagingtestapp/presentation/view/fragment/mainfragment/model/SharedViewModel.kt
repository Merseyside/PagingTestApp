package com.merseyside.pagingtestapp.presentation.view.fragment.mainfragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merseyside.pagingtestapp.domain.PropertyModel

class SharedViewModel : ViewModel() {

    private val TAG = javaClass.simpleName

    private val propertyContainer = MutableLiveData<PropertyModel>()

    fun getPropertyContainerLiveData() : LiveData<PropertyModel> {
        return propertyContainer
    }

    fun shareProperty(property : PropertyModel) {
        propertyContainer.value = property
    }

}