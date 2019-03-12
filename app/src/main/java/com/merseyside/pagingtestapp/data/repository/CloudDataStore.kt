package com.merseyside.pagingtestapp.data.repository

import com.merseyside.propertyapi.entity.response.PropertyResponse
import com.merseyside.propertyapi.net.PropertyApi
import io.reactivex.Single
import javax.inject.Inject

class CloudDataStore @Inject constructor(private val api : PropertyApi) : PropertyDataStore {

    override fun getProperties(limit: Int, offset: Int): Single<PropertyResponse> {
        return api.getProperties(limit, offset)
    }
}