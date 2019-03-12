package com.merseyside.pagingtestapp.data.repository

import com.merseyside.pagingtestapp.data.entity.mapper.PropertyDataMapper
import com.merseyside.pagingtestapp.domain.Property
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
        private val propertyDataStoreFactory: PropertyDataStoreFactory,
        private val propertyDataMapper: PropertyDataMapper
): DataRepository {

    override fun getProperties(limit: Int, offset: Int): Single<List<Property>> {
        val dataStore = propertyDataStoreFactory.create()

        return dataStore.getProperties(limit, offset)
                .map {response ->
                    propertyDataMapper.transform(response)
                }
    }


}