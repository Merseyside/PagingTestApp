package com.merseyside.pagingtestapp.data.repository

import android.content.Context
import com.merseyside.propertyapi.net.PropertyApi
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import javax.inject.Inject

class PropertyDataStoreFactory @Inject constructor(@ApplicationContext private val context : Context) {

    fun create() : PropertyDataStore {

        return createCloudDataStore() //no cache

    }

    fun createCloudDataStore() : CloudDataStore {
        return CloudDataStore(PropertyApi(context))
    }
}