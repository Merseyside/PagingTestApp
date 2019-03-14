package com.merseyside.pagingtestapp.data.repository

import com.merseyside.pagingtestapp.data.entity.mapper.PropertyDataMapper
import com.merseyside.pagingtestapp.data.exception.NoNetworkConnectionException
import com.merseyside.pagingtestapp.domain.PropertyModel
import com.merseyside.pagingtestapp.domain.repository.DataRepository
import com.merseyside.pagingtestapp.utils.Utils
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
        private val propertyDataStoreFactory: PropertyDataStoreFactory,
        private val propertyDataMapper: PropertyDataMapper
): DataRepository {

    private val TAG = javaClass.simpleName

    override fun getProperties(limit: Int, offset: Int): Single<List<PropertyModel>> {

        return if (Utils.isOnline()) {
            val dataStore = propertyDataStoreFactory.create()

            dataStore.getProperties(limit, offset)
                    .map { response ->
                        propertyDataMapper.transform(response)
                    }

        } else {
            Single.error(NoNetworkConnectionException())
        }
    }


}