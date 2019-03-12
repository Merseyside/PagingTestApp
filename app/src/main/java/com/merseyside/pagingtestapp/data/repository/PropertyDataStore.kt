package com.merseyside.pagingtestapp.data.repository

import com.merseyside.propertyapi.entity.response.PropertyResponse
import io.reactivex.Single

interface PropertyDataStore {

    fun getProperties(limit : Int, offset : Int) : Single<PropertyResponse>
}