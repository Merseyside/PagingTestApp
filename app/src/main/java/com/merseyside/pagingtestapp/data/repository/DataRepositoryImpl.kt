package com.merseyside.pagingtestapp.data.repository

import android.util.Log
import com.merseyside.pagingtestapp.data.entity.mapper.PropertyDataMapper
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
        private val propertyDataStoreFactory: PropertyDataStoreFactory,
        private val propertyDataMapper: PropertyDataMapper
): DataRepository {

    private val TAG = javaClass.simpleName

    override fun getProperties(limit: Int, offset: Int): Single<List<PropertyModel>> {
        val dataStore = propertyDataStoreFactory.create()

        return dataStore.getProperties(limit, offset)
                .map {response ->
                    Log.d(TAG, "${response.propertyList.size}")
                    propertyDataMapper.transform(response)
                }
    }


}